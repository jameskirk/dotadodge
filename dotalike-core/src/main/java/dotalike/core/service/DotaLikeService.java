package dotalike.core.service;

import java.util.Date;
import java.util.List;

import dotalike.common.model.Player;
import dotalike.common.model.Vote;
import dotalike.common.model.external.MatchHistory;

public interface DotaLikeService {

    public List<Player> getPlayers(List<Integer> playersId);
    
    public List<MatchHistory> getMatchHistory(List<Integer> playerId);
    
    public void report(Vote report, Integer toSteamId, Date toMatchDate); 
}
