/**
 * Basic skeleton class for describing Entities using RDF.
 * 
 * Implements a method for converting JSON entities to RDF.
 * 
 * @author Chris (c.baillie@abdn.ac.uk)
 */

package rdf;

import entity.JsonEntity;

public abstract class RdfEntity {
	
	public abstract void jsonEntityToRdfEntity(JsonEntity entity);

}
