/**
 * Enables the writing a SPARQL updates to fuseki
 * 
 * @author Chris (c.baillie@abdn.ac.uk)
 */

package sparql;

import com.hp.hpl.jena.sparql.modify.UpdateProcessRemote;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;

public class SPARQLUpdate {
	
	private String endpoint;
	
	/**
	 * Constructor
	 * 
	 * @param e The URL of the endpoint
	 */
	public SPARQLUpdate(String e)
	{
		endpoint = e;
	}
	
	/**
	 * Perform a SPARQL update
	 * 
	 * @param updateQuery The SPARQL query to be executed
	 * @return boolean indicating success
	 */
	public boolean doSPARQLUpdate(String updateQuery)
	{
		log("doSPARQLUpdate",updateQuery);
		log("doSPARQLUpdate",endpoint);
		
		try
		{
			UpdateRequest ur = UpdateFactory.create();
			ur.add(updateQuery);
			UpdateProcessRemote r = new UpdateProcessRemote(ur,endpoint);
			r.execute();
			log("doSPARQLUpdate","SPARQL update sent");
			return true;
		}
		catch(Exception ex)
		{
			log("doSPARQLUpdate","Error sending SPARQL update...");
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Send messages to the user
	 * 
	 * @param method The method from which the message originated
	 * @param msg The message to be displayed
	 */
	public void log(String method, String msg)
	{
		System.out.println("[SPARQLUpdate] " + method + " : " + msg);
	}

}
