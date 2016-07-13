package dotalike.service.db;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceException;

import org.hibernate.collection.internal.PersistentMap;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.CollectionEntry;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.internal.SessionImpl;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.type.EntityType;
import org.hibernate.type.Type;

import dotalike.service.misc.GuiceFactory;

public class JPA {

	private static EntityManager em;

	public static EntityManager em() {
		if (em == null) {
			em = GuiceFactory.getInjector().getInstance(EntityManager.class);
		}
		return em;
	}

	public static <T> T findById(Class<T> object, int id) {
		return em().find(object, id);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> findAll(Class<T> object) {
		return em().createQuery("Select t from " + object.getSimpleName() + " t").getResultList();
	}

	public static void save(Object object) {
		EntityTransaction tx = em().getTransaction();
		tx.begin();
		if (!em().contains(object)) {
			em().persist(object);
			// PlayPlugin.postEvent("JPASupport.objectPersisted", this);
		}
		avoidCascadeSaveLoops.set(new HashSet<Object>());
		try {
			saveAndCascade(object, true);
		} finally {
			avoidCascadeSaveLoops.get().clear();
		}
		try {
			em().flush();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof GenericJDBCException) {
				throw new PersistenceException(((GenericJDBCException) e.getCause()).getSQL(), e);
			} else {
				throw e;
			}
		}
		avoidCascadeSaveLoops.set(new HashSet<Object>());
		try {
			saveAndCascade(object, true);
		} finally {
			avoidCascadeSaveLoops.get().clear();
			tx.commit();
		}
	}

	public static void delete(Object object) {
		EntityTransaction tx = em().getTransaction();
		tx.begin();
		try {
			avoidCascadeSaveLoops.set(new HashSet<Object>());
			try {
				saveAndCascade(object, true);
			} finally {
				avoidCascadeSaveLoops.get().clear();
			}
			em().remove(object);
			try {
				em().flush();
			} catch (PersistenceException e) {
				if (e.getCause() instanceof GenericJDBCException) {
					throw new PersistenceException(((GenericJDBCException) e.getCause()).getSQL(), e);
				} else {
					throw e;
				}
			}
			avoidCascadeSaveLoops.set(new HashSet<Object>());
			try {
				saveAndCascade(object, false);
			} finally {
				avoidCascadeSaveLoops.get().clear();
			}
			// PlayPlugin.postEvent("JPASupport.objectDeleted", this);
		} catch (PersistenceException e) {
			throw e;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			// tx.commit();
		}
	}

	// public Object _key() {
	// return Model.Manager.factoryFor(this.getClass()).keyValue(this);
	// }

	// ~~~ SAVING
	public static transient ThreadLocal<Boolean> willBeSaved = new ThreadLocal<Boolean>(); // TODO not static
	
	static transient ThreadLocal<Set<Object>> avoidCascadeSaveLoops = new ThreadLocal<Set<Object>>();

	private static void saveAndCascade(Object object, Boolean willBeSaved) {
		JPA.willBeSaved.set(willBeSaved);
		if (avoidCascadeSaveLoops.get().contains(object)) {
			return;
		} else {
			avoidCascadeSaveLoops.get().add(object);
			if (willBeSaved) {
				// PlayPlugin.postEvent("JPASupport.objectUpdated", this);
			}
		}
		// Cascade save
		try {
			Set<Field> fields = new HashSet<Field>();
			Class<?> clazz = object.getClass();

			while (!clazz.equals(Object.class)) {
				Collections.addAll(fields, clazz.getDeclaredFields());
				clazz = clazz.getSuperclass();
			}
			for (Field field : fields) {
				field.setAccessible(true);
				if (Modifier.isTransient(field.getModifiers())) {
					continue;
				}
				boolean doCascade = false;
				if (field.isAnnotationPresent(OneToOne.class)) {
					doCascade = cascadeAll(field.getAnnotation(OneToOne.class).cascade());
				}
				if (field.isAnnotationPresent(OneToMany.class)) {
					doCascade = cascadeAll(field.getAnnotation(OneToMany.class).cascade());
				}
				if (field.isAnnotationPresent(ManyToOne.class)) {
					doCascade = cascadeAll(field.getAnnotation(ManyToOne.class).cascade());
				}
				if (field.isAnnotationPresent(ManyToMany.class)) {
					doCascade = cascadeAll(field.getAnnotation(ManyToMany.class).cascade());
				}
				if (doCascade) {
					Object value = field.get(object);
					if (value != null) {
						if (value instanceof PersistentMap) {
							if (((PersistentMap) value).wasInitialized()) {

								cascadeOrphans(object, (PersistentCollection) value, willBeSaved);

								for (Object o : ((Map<?, ?>) value).values()) {
									saveAndCascadeIfJPABase(o, willBeSaved);
								}
							}
						} else if (value instanceof PersistentCollection) {
							PersistentCollection col = (PersistentCollection) value;
							if (((PersistentCollection) value).wasInitialized()) {

								cascadeOrphans(object, (PersistentCollection) value, willBeSaved);

								for (Object o : (Collection<?>) value) {
									saveAndCascadeIfJPABase(o, willBeSaved);
								}
							} else {
								cascadeOrphans(object, col, willBeSaved);

								for (Object o : (Collection<?>) value) {
									saveAndCascadeIfJPABase(o, willBeSaved);
								}
							}
						} else if (value instanceof Collection) {
							for (Object o : (Collection<?>) value) {
								saveAndCascadeIfJPABase(o, willBeSaved);
							}
						} /*
							 * else if (value instanceof HibernateProxy && value
							 * instanceof JPABase) { if (!((HibernateProxy)
							 * value).getHibernateLazyInitializer().
							 * isUninitialized()) { ((JPABase) ((HibernateProxy)
							 * value).getHibernateLazyInitializer().
							 * getImplementation())
							 * .saveAndCascade(willBeSaved); } } else if (value
							 * instanceof JPABase) { ((JPABase)
							 * value).saveAndCascade(willBeSaved); }
							 */
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("During cascading save()", e);
		}
	}

	private static void cascadeOrphans(Object base, PersistentCollection persistentCollection, boolean willBeSaved) {
		SessionImpl session = ((SessionImpl) JPA.em().getDelegate());
		PersistenceContext pc = session.getPersistenceContext();
		CollectionEntry ce = pc.getCollectionEntry(persistentCollection);

		if (ce != null) {
			CollectionPersister cp = ce.getLoadedPersister();
			if (cp != null) {
				Type ct = cp.getElementType();
				if (ct instanceof EntityType) {
					EntityEntry entry = pc.getEntry(base);
					String entityName = entry.getEntityName();
					entityName = ((EntityType) ct).getAssociatedEntityName(session.getFactory());
					if (ce.getSnapshot() != null) {
						Collection<?> orphans = ce.getOrphans(entityName, persistentCollection);
						for (Object o : orphans) {
							saveAndCascadeIfJPABase(o, willBeSaved);
						}
					}
				}
			}
		}
	}

	private static void saveAndCascadeIfJPABase(Object o, boolean willBeSaved) {
		if (Arrays.asList(o.getClass().getAnnotations()).contains(Entity.class)) {
			saveAndCascade(o, willBeSaved);
		}
	}

	private static boolean cascadeAll(CascadeType[] types) {
		for (CascadeType cascadeType : types) {
			if (cascadeType == CascadeType.ALL || cascadeType == CascadeType.PERSIST) {
				return true;
			}
		}
		return false;
	}

	public boolean isPersistent() {
		return em().contains(this);
	}

}
