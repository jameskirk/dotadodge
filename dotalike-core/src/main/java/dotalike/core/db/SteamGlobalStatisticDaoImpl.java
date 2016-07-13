package dotalike.core.db;

import dotalike.common.model.external.PlayerGlobalDetails;
import dotalike.core.dao.GlobalStatisticDao;
import dotalike.common.model.external.Party;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anstepan on 18.12.2015.
 */
public class SteamGlobalStatisticDaoImpl implements GlobalStatisticDao {
    @Override
    public List<PlayerGlobalDetails> getPlayersDetails(List<Integer> id) {
        return null;
    }

    public List<Party> getPlayersInPartyInfo(List<Integer> ids) {
        Set<Party> playersInPartyInfo = new HashSet<>();
        for (Integer id : ids){

            List<Integer> friendsIdList = new ArrayList<>(findFriendsList(id));
         //   ids.


        }
        List<Party> res = new ArrayList<>();
        res.addAll(playersInPartyInfo);
            return res;
    }

    public List<Integer> findFriendsList(Integer id) {
        List<Integer> friendsIdList = new ArrayList<>();
        //TODO make steam connect
        return friendsIdList;
    }
}
