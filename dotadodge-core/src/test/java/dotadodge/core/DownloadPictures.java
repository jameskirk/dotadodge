package dotadodge.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import dotadodge.core.misc.Configuration;
import dotadodge.core.misc.Configuration.ConfigurationKey;
import dotadodge.core.model.external.PlayerGlobalDetails;
import us.codecraft.xsoup.Xsoup;

public class DownloadPictures {
	
	//@Test
	public void download() throws IOException {
		PlayerGlobalDetails retVal = new PlayerGlobalDetails();
    	if (!Configuration.getInstance().read(ConfigurationKey.PROXY).isEmpty()) {
            System.setProperty("http.proxyHost", Configuration.getInstance().read(ConfigurationKey.PROXY));
            System.setProperty("http.proxyPort", Configuration.getInstance().read(ConfigurationKey.PROXY_PORT));
        }

        List<String> winRatePlayers = new ArrayList();
        String urlC = new String("http://ru.dotabuff.com/heroes/winning");
        Document doc = Jsoup.connect(urlC)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .timeout(2000)
                .followRedirects(true)
                .get();
        int i = 0;
        while (true) {
	        try {
	        	String hero = Xsoup.compile("//*[@class='sortable']/*/tr[" + new Integer(i+1).toString() + "]//a[@class='link-type-hero']").evaluate(doc).getElements().get(0).text();
	        	String src = Xsoup.compile("//*[@class='sortable']/*/tr[" + new Integer(i+1).toString() + "]//img[@class='image-hero image-icon']").evaluate(doc).getElements().get(0).attr("src");
	        	System.out.println(hero + src);

	            try {
	            	Response resultImageResponse = Jsoup.connect("http://www.dotabuff.com" + src)
	            			.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
	                        .referrer("http://www.google.com")
	                        .timeout(2000)
	                        .followRedirects(true)
					                            .ignoreContentType(true).execute();
					
					//output here
					FileOutputStream out = (new FileOutputStream(new java.io.File("C:\\Temp\\dota\\" + hero + ".jpg")));
					out.write(resultImageResponse.bodyAsBytes());
					out.close();
	                
	            } catch (IOException e) {
	            	e.printStackTrace();
	            }
	        	i++;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	break;
			}
        }
	}

}
