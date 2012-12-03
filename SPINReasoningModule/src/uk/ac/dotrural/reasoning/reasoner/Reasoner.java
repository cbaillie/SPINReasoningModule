/**
 * Provides access to a SPIN reasoner
 * 
 * @author Chris (c.baillie@abdn.ac.uk)
 */

package uk.ac.dotrural.reasoning.reasoner;

import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.system.SPINModuleRegistry;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Reasoner {
	
	private Model rules = ModelFactory.createDefaultModel();
	
	/**
	 * Create a new instance of Reasoner
	 * 
	 * @param rulesLocation The location of the file containing the rules to guide reasoning
	 * @param rulesNotation The notation used to describe the rules
	 * @param localFile Whether the rules file is local or remote
	 */
	public Reasoner(String rulesLocation, String rulesNotation, boolean localFile)
	{
		System.out.println("[Reasoner] creating reasoner with rules from " + rulesLocation);
		if(localFile)
		{
			rules = FileManager.get().loadModel(rulesLocation, rulesNotation);
		}
		else
		{
			try
			{
				rules.read(rulesLocation, rulesNotation);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Execute the reasoner
	 * 
	 * @param entityDescription The entity description to be reasoned about
	 * @return Set of inferred triples
	 */
	public Model performReasoning(OntModel entityDescription)
	{
		Model nTriples = ModelFactory.createDefaultModel();
		OntModel reasoningModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		reasoningModel.add(rules);
		reasoningModel.addSubModel(entityDescription);
		reasoningModel.addSubModel(nTriples);
		
		try
		{
			SPINModuleRegistry.get().init();
			SPINModuleRegistry.get().registerAll(reasoningModel, null);
			SPINInferences.run(reasoningModel, nTriples, null, null, false, null);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println("[Reasoner] performReasoning : Inferred " + nTriples.size() + " triples");
		
		return nTriples;
	}

}
