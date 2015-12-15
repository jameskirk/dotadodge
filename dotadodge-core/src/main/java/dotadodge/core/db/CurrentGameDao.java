package dotadodge.core.db;

import com.google.inject.Inject;

import dotadodge.core.file.ServerLogParser;
import dotadodge.core.model.Match;

public class CurrentGameDao {
    
    @Inject
    private ServerLogParser serverLogParser;
    
    public Match getCurrentMatch() {
	return serverLogParser.parseOneTime();
    }

}
