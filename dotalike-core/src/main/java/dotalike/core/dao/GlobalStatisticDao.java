package dotalike.core.dao;

import java.util.List;

import dotalike.common.model.Player;
import dotalike.common.model.external.MatchHistory;

/**
 * Created by anstepan on 18.12.2015.
 */
public interface GlobalStatisticDao {
	
    public List<Player> getPlayersDetails(List<Integer> id);
    
    public List<MatchHistory> getMatchHistory(List<Integer> ids);
    
}
