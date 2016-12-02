package main.java;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

@Path("/solum")
public class OpenStackMeetingsResource {
	
	public OpenStackMeetingsResource() {
		
	}
	
	@GET
	@Path("/table")
	@Produces("text/html")
	public String getTable() {
		Elements e;
		int count;
		String code = "<table><tr><th>Year</th><th>Number of meetings</th></tr>";
		e = getElements("http://eavesdrop.openstack.org/meetings/solum_team_meeting/2013/");
		count = getCount(e);
		code += "<tr><td>2013</td><td>"+count+"</td></tr>";
		e = getElements("http://eavesdrop.openstack.org/meetings/solum_team_meeting/2014/");
		count = getCount(e);
		code += "<tr><td>2014</td><td>"+count+"</td></tr>";
		e = getElements("http://eavesdrop.openstack.org/meetings/solum_team_meeting/2015/");
		count = getCount(e);
		code += "<tr><td>2015</td><td>"+count+"</td></tr>";
		e = getElements("http://eavesdrop.openstack.org/meetings/solum_team_meeting/2016/");
		count = getCount(e);
		code += "<tr><td>2016</td><td>"+count+"</td></tr>";
		code += "</table>";
		return code;
	}
	
	public int getCount(Elements e){
		int count = 0;
		for (Element element : e){
			if (element.text().contains(".log.html")){
				count++;
			}
		}
		return count;
	}
	
	public Elements getElements(String source) {
		
	    Document doc = null;
		try {
			doc = Jsoup.connect(source).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Elements links = doc.select("a");
		    return links;			
		}
		else {
			return null;
		}

	}
}