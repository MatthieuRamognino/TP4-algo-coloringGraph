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
	
	public int getIdMax(List<Integer> list){
	    int max = Integer.MIN_VALUE;
	    int idMax = 0;
	    for(int i=0; i<list.size(); i++){
	        if(list.get(i) > max){
	            max = list.get(i);
	            idMax = i;
	        }
	    }
	    return idMax;
	}
	
	public List<Integer> coloringAlgorithm() {
//		Initialisation de la liste colors
		for (int i=0; i<this.vertices().size(); i++) {
			// 0 correspond à un sommet sans couleur
			colors.add(0);
		}
		
		// parcours le graph et crée une liste où chaque valeur correspond au degrée d'un sommet
		List <Integer> degrees = new ArrayList<>();
		// remplie la liste
		for (int sommet=0; sommet<this.vertices().size(); sommet++) {
			degrees.add(this.outDegree(this.vertices().get(sommet)));
		}

		// tri cette liste dans l'ordre décroissant
		// pour cela on passe par une deuxieme liste: degreesTrie qui sera notre nouvelle liste de travaille
		List <Integer> degreesTrie = new ArrayList<>();
		for (int sommet=0; sommet<this.vertices().size(); sommet++) {
			int idSommetMax = getIdMax(degrees);
			degreesTrie.add(idSommetMax);
			degrees.set(idSommetMax, 0);
		}
		
		// tant que le graphe nest pas totalement coloree
		while(this.colors.contains(0)) {
			// parcours la liste degreesTrie et choisie le premier sommet qui nest pas colore
			for (int i=0;i<degreesTrie.size();i++) {
				if (this.colors.get(degreesTrie.get(i)) == 0) {
				// Associate an unused color to the first uncolored vertex,
				// on attribut a ce sommet une nouvelle couleur
				this.colors.set(degreesTrie.get(i), colors.get(getIdMax(this.colors))+1);
				System.out.println("parcours la liste degreesTrie et selectionne le sommet: " + degreesTrie.get(i) 
						+ " on lui associe la couleur : " + colors.get(degreesTrie.get(i)));
				
				//	associate this color to each uncolored vertex un-adjacent to a
				//	vertex of this color (taking them in order to their degree
				//	values).
				
				// Parcours TOUS les sommets du graphe (dans l'ordre des degrees) 
				// prend un sommet regarde si ce sommet nest pas déja coloré
				// de plus regarde si il ne possède pas dans ses voisins un sommets colorée
				for (int k=0; k<degreesTrie.size(); k++) {
					List<Vertex> voisinsDuSommets = this.outNeighbors(this.vertices().get(degreesTrie.get(k)));
					// si le sommet nes pas coloré
					if (this.colors.get(degreesTrie.get(k)) == 0) {
						// on regarde ses voisins
						boolean voisinColoree = false;
						for (int j=0;j<voisinsDuSommets.size();j++) {
							// on regarde si ils sont coloré ou non
							if (this.colors.get((int) voisinsDuSommets.get(j)) != 0) {
								// System.out.println("possède un voisin coloré");
								voisinColoree = true;
							}	
						}
						// si il ne possède aucun voisin coloree alors on le colore dans la même couleur que le sommet degreesTrie
						if (!voisinColoree) {
							System.out.println(" colore le sommets : "+ degreesTrie.get(k)
							+ " dans la couleur : " + colors.get(degreesTrie.get(i)));
							this.colors.set(degreesTrie.get(k), colors.get(degreesTrie.get(i)));
						}
					}
				}
			}
		}
	}
	return this.colors;
}


	public List<Integer> getColors() {
		return colors;
	}

	public void setColors(List<Integer> colors) {
		this.colors = colors;
	}
	
	
}
