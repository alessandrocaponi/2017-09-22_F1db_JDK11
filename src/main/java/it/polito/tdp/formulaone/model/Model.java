package it.polito.tdp.formulaone.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;


public class Model {
	
	Graph<Race, DefaultWeightedEdge> grafo;
	Map<Integer, Race> raceIdMap;
	
	public String doCreaGrafo(Season season) {
		
		FormulaOneDAO dao = new FormulaOneDAO();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		raceIdMap = new HashMap<>();
		for(Race r: dao.getRaceSeason(season.getYear())) {
			
			raceIdMap.put(r.getRaceId(), r);
			grafo.addVertex(r);
			
		}
		
		
		List<EdgeAndWeight> archi = new ArrayList<>(dao.getEdgeAndWeight(season.getYear()));
		Collections.sort(archi);
		for(EdgeAndWeight e: archi)
			Graphs.addEdge(grafo, raceIdMap.get(e.getRaceId1()), raceIdMap.get(e.getRaceId2()), e.getPeso());
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n\n";
		
		
		int pesoMax = archi.get(0).getPeso();
		result += "Gli archi di peso massimo sono:\n";
		for(EdgeAndWeight e: archi)
			if(e.getPeso()== pesoMax)
				result+= raceIdMap.get(e.getRaceId1())+" ----- "+raceIdMap.get(e.getRaceId2())+"\t\t"+e.getPeso()+"\n";
		
		return result;
		
	}

	
	public List<Season> getAllSeasons() {
		FormulaOneDAO dao = new FormulaOneDAO();
		return dao.getAllSeasons();
	}

}
