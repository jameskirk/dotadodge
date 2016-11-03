package dotalike.core.misc;

import com.google.inject.AbstractModule;

import dotalike.core.dao.GlobalStatisticDao;
import dotalike.core.db.DotabuffGlobalStatisticDaoImpl;
import dotalike.core.service.DotaLikeService;
import dotalike.core.service.DotaLikeServiceImpl;

public class CoreModule extends AbstractModule{

    @Override
    protected void configure() {
    	bind(DotaLikeService.class).to(DotaLikeServiceImpl.class);
    	bind(GlobalStatisticDao.class).to(DotabuffGlobalStatisticDaoImpl.class);
    }

}
