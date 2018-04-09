
public class Main extends GraphFactory{

	public static void main(String[] args) {
		UndirectedAdjGraph<Integer> g=createDiGraphFromTextFile("Graph/Tournament-data.txt");
		System.out.println("les voisins d'un certain sommets" + g.outNeighbors(0));
		System.out.println("le degree d'un certain voisin " + g.outDegree(0));
		g.coloringAlgorithm();
		System.out.println("ma liste de colors " + g.getColors().get(0));

	}

}
