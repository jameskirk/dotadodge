package dotadodge.core.db;

import dotadodge.core.model.external.PlayerGlobalDetails;
import dotadodge.core.model.external.PlayersInPartyInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anstepan on 18.12.2015.
 */
public class SteamGlobalStatisticDaoImpl implements GlobalStatisticDao {
    @Override
    public List<PlayerGlobalDetails> getPlayerStats(List<Integer> id) {
        return null;
    }

    public List<PlayersInPartyInfo> getPlayersInPartyInfo(List<Integer> ids) {
        Set<PlayersInPartyInfo> playersInPartyInfo = new HashSet<>();
        for (Integer id : ids){

            List<Integer> friendsIdList = new ArrayList<>(findFriendsList(id));
         //   ids.


        }
        List<PlayersInPartyInfo> res = new ArrayList<>();
        res.addAll(playersInPartyInfo);
            return res;
    }

    public List<Integer> findFriendsList(Integer id) {
        List<Integer> friendsIdList = new ArrayList<>();
        //TODO make steam connect
        return friendsIdList;
    }
}
