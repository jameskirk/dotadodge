package dotadodge.core.dao;

import java.util.Date;
import java.util.List;

import dotalike.common.model.Player;
import dotalike.common.model.Report;

public interface CustomStatisticDao {
    
    public List<Player> getPlayers(List<Integer> ids);
    
    public void reportPlayer(Report report, int toSteamId, Date toMatchDate);

}
