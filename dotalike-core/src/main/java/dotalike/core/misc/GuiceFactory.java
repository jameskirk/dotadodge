package dotalike.core.misc;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceFactory {
    
    private static Injector injector;
    
    public static Injector getInjector() {
	if (injector == null) {
	    injector = Guice.createInjector(new CoreModule());
	}
        return injector;
    }

}
