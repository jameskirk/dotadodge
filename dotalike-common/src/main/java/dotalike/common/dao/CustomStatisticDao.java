package dotalike.common.dao;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import dotalike.common.model.Player;
import dotalike.common.model.Report;
import dotalike.common.model.dto.PlayersList;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface CustomStatisticDao {
	
	@WebMethod
	public String getTime(String str);
    
	@WebMethod
    public PlayersList getPlayers(List<Integer> ids);
    
    //public void reportPlayer(Report report, int toSteamId, Date toMatchDate);

}
