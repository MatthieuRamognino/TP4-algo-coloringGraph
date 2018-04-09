import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UndirectedAdjGraph<Vertex extends Comparable<Vertex>> extends Graph<Vertex> {
	
	private List<Integer> colors= new ArrayList<>();
	private final Map<Vertex, List<Vertex>> adjacency = new TreeMap<>();

	@Override
	public void addEdge(Vertex s, Vertex t) {
		m++;		
		if(!adjacency.containsKey(s))
			addVertex(s);
		if(!adjacency.containsKey(t))
			addVertex(t);
		
		List<Vertex> se = adjacency.get(s);
		se.add(t);
		List<Vertex> te = adjacency.get(t);
		te.add(s);
	}

	@Override
	public void addVertex(Vertex v) {
		n++;
		adjacency.put(v, new LinkedList<>());
		
	}
	
	public void addColor(Vertex v, int couleur) {
		
		colors.add((int) v,couleur);
	}

	@Override
	public List<Vertex> vertices() {
		List<Vertex> out = new ArrayList<>();
		out.addAll(adjacency.keySet());
		
		return out;
	}

	
	public List<Vertex> inEdges(Vertex vertice) {
		return adjacency.get(vertice);
	}

	
	public List<Vertex> outEdges(Vertex vertice) {
		
		return inEdges(vertice);
	}

	@Override
	public List<Vertex> inNeighbors(Vertex v) {
		List<Vertex> out = new LinkedList<>();
		
		for(Vertex e: adjacency.get(v))
			out.add(e);
		
		return out;
	}

	@Override
	public List<Vertex> outNeighbors(Vertex v) {
		
		return inNeighbors(v);
	}

	@Override
	public int inDegree(Vertex v) {
		
		return adjacency.get(v).size();
	}

	@Override
	public int outDegree(Vertex v) {
		return inDegree(v);
	}
	
	public List<Integer> coloringAlgorithm() {
//		Initialisation de la list colors
		for (int i=0; i<this.vertices().size(); i++) {
			// 0 correspond à un sommet sans couleur
			colors.add(0);
		}
//		Order vertices of G by decreasing degree
		// parcours le graph pour en tire une liste des sommets avec leurs degré
		// tri cette liste dans l'ordre décroissant (en gardant le lien entre chaque sommet et son degrée respectif
//		2. Associate an unused color to the first uncolored vertex,
//		first uncolored vertex = listeDegreeDecroissant[0]
//		colors.set(this.vertices().get(// mon sommet)
//		associate this color to each uncolored vertex un-adjacent to a
//		vertex of this color (taking them in order to their degree
//		values).
		
//		3. Iterate step 2 until a full coloring is reached
		return this.colors;
	}

	public List<Integer> getColors() {
		return colors;
	}

	public void setColors(List<Integer> colors) {
		this.colors = colors;
	}
	
	
}
