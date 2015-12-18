package dotadodge.core.service;

import java.util.Date;
import java.util.List;

import dotadodge.core.model.Match;
import dotadodge.core.model.Player;
import dotadodge.core.model.Report;

public interface DotaDodgeService {
    
    public List<Player> getPlayers(List<Integer> playersId);
    
    public List<Match> getMatchHistory(Integer playerId);
    
    public void report(Report report, Integer toSteamId, Date toMatchDate); 
}
