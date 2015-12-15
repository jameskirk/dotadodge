package dotadodge.core.main;

import com.google.inject.Inject;

import dotadodge.core.db.ApplicationInitializer;
import dotadodge.core.db.CurrentGameDao;
import dotadodge.core.db.CustomStatisticDao;

public class DotaDodge {
    
    @Inject
    private ApplicationInitializer applicationInitializer;
    
    @Inject
    private CustomStatisticDao customStatisticDao;
    
    @Inject
    private CurrentGameDao currentGameDao;
    
    public DotaDodge() {
    }
    
    public void run() {
	applicationInitializer.start();
    }
    
    public void dodge() {
	//TODO
    }
    
    public CustomStatisticDao getCustomStatisticDao() {
        return customStatisticDao;
    }

    public ApplicationInitializer getApplicationInitializer() {
        return applicationInitializer;
    }

    public CurrentGameDao getCurrentGameDao() {
        return currentGameDao;
    }
    
}
