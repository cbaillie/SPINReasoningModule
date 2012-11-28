/**
 * Utility class for reading JSON representations of entities
 * from a web service
 * 
 * @author Chris (c.baillie@abdn.ac.uk)
 */

package entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class EntityUtilities {
	
	/**
	 * Request JSON representation of an entity from a given URL
	 * 
	 * @param url The URI of the entity
	 * @return JSON string describing the entity
	 */
	public static String getModelJson(String url)
	{
		System.out.println("[EntityUtilities] getModelJson :  Querying for Entity [" + url + "]");
		StringBuilder in = new StringBuilder();
		String tmp = "";
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			while((tmp = br.readLine()) != null)
			{
				in.append(tmp);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return in.toString();
	}

}
