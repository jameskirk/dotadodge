package dotadodge.core.db;

import dotadodge.core.model.external.PlayerGlobalDetails;

import java.util.List;

/**
 * Created by anstepan on 18.12.2015.
 */
public interface GlobalStatisticDao {
    public List<PlayerGlobalDetails> getPlayerStats(List<Integer> id);
}
