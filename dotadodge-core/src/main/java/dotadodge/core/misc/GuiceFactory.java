package dotadodge.core.misc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

public class GuiceFactory {
    
    public static final String PU_NAME_TEST = "examplePU";
    
    public static final String PU_NAME_PROD = "prodPU";
    
    private static Injector injector;
    
    public static Injector getInjector() {
	if (injector == null) {
	    injector = Guice.createInjector(new JpaPersistModule(PU_NAME_TEST));
	}
        return injector;
    }

}
