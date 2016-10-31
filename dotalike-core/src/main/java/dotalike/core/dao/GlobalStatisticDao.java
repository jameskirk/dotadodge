package dotalike.core.dao;

import java.util.List;

import dotalike.common.model.Player;

/**
 * Created by anstepan on 18.12.2015.
 */
public interface GlobalStatisticDao {
	
    public List<Player> getPlayersDetails(List<Integer> id);
    
}
