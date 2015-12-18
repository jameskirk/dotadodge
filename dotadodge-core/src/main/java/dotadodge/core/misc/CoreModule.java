package dotadodge.core.misc;

import com.google.inject.AbstractModule;

import dotadodge.core.service.DotaDodgeService;
import dotadodge.core.service.DotaDodgeServiceImpl;

public class CoreModule extends AbstractModule{

    @Override
    protected void configure() {
	bind(DotaDodgeService.class).to(DotaDodgeServiceImpl.class);
	
    }

}
