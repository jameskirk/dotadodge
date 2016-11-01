package dotalike.core.service;

import java.util.Date;
import java.util.List;

import dotalike.common.model.Match;
import dotalike.common.model.Player;
import dotalike.common.model.Vote;

public interface DotaLikeService {

    public List<Player> getPlayers(List<Integer> playersId);
    
    public List<Match> getMatchHistory(Integer playerId);
    
    public void report(Vote report, Integer toSteamId, Date toMatchDate); 
}
