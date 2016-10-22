package dotalike.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class ApplicationInitializer {
    
    private final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
    
    private PersistService persistenceService;
    
    private static boolean initialized = false;

	@Inject
	public ApplicationInitializer(PersistService persistenceService) {
		// start JPA
		this.persistenceService = persistenceService;
		;
	}

	public void start() {
		if (initialized) {
			logger.warn("application is already initialized");
			return;
		}
		persistenceService.start();
		initialized = true;
	}
}
