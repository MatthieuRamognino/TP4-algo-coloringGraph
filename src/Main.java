
public class Main extends GraphFactory{

	public static void main(String[] args) {
		UndirectedAdjGraph<Integer> g=createDiGraphFromTextFile("Graph/Tournament-data.txt");
		System.out.println("liste de tous les sommets de notre graph ");
		System.out.println(g.vertices().toString());
		
		g.coloringAlgorithm();
		System.out.println("ma liste de colors " + g.getColors().toString());
		System.out.println("le nombre de couleurs sélève à :  " + g.getColors().get(g.getIdMax(g.getColors())));

		System.out.println("ma liste de colors contient-elle une 'uncolored value' ? " + g.getColors().contains(0));
		System.out.println("si oui, sont indice est : " + g.getColors().indexOf(0));
	}

}
