import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphFactory {
	
	public static  UndirectedAdjGraph<Integer> createDiGraphFromTextFile(String path) {
		List<String> joueurs= new ArrayList<>();
		List<Integer> matchsDuJoueur= new ArrayList<>();
		UndirectedAdjGraph<Integer> g = new UndirectedAdjGraph<>();
		try(Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
			while(scan.hasNext()) {
				for(int i=0;i<9;i++) {
					if(scan.next()!="vs") {
						String joueur = scan.next();
						if(joueurs.contains(joueur)==false) {
							joueurs.add(joueur);
						}
					}
				}
				g.addEdge(u, v);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
}

