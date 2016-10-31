package dotalike.core.misc;

import com.google.inject.AbstractModule;

import dotalike.core.dao.GlobalStatisticDao;
import dotalike.core.db.DotabuffGlobalStatisticDaoImpl;
import dotalike.core.service.DotaLikeService;
import dotalike.core.service.DotaDodgeServiceImpl;

public class CoreModule extends AbstractModule{

    @Override
    protected void configure() {
    	bind(DotaLikeService.class).to(DotaDodgeServiceImpl.class);
    	bind(GlobalStatisticDao.class).to(DotabuffGlobalStatisticDaoImpl.class);
    }

}
