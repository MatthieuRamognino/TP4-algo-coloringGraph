import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphFactory {
	
	public static  UndirectedAdjGraph<Integer> createDiGraphFromTextFile(String path) {
		List<Integer> joueurs= new ArrayList<>();
		List<Integer> matchsDuJoueur= new ArrayList<>();
		UndirectedAdjGraph<Integer> g = new UndirectedAdjGraph<>();
		
			List<Integer> nombres = new ArrayList<>();
			try {
				for (String ligne : Files.readAllLines(Paths.get(path))) {
					 for (String chaine : ligne.split(" ")){
						 if(chaine.length()>2) {
							 String test = chaine.replace("J-","");
							 int test2= Integer.parseInt(test);
							 System.out.println(test2);
							 nombres.add(test2);
						 }
					 }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<nombres.size();i++) {
				if(joueurs.contains(nombres.get(i))==false) {
					joueurs.add(nombres.get(i));
					matchsDuJoueur.clear();
					for(int j=i;j<nombres.size();j++) {
						if(nombres.get(j)==nombres.get(i)) {
							matchsDuJoueur.add(j/10);
						}
					}
					for(int k=0;k<matchsDuJoueur.size();k++) {
						for(int l=k+1;l<matchsDuJoueur.size();l++) {
							if(g.vertices().contains(matchsDuJoueur.get(k)) && 
									g.inNeighbors(matchsDuJoueur.get(k)).contains(matchsDuJoueur.get(l))==false || 
									g.vertices().contains(matchsDuJoueur.get(k))==false) {
								g.addEdge(matchsDuJoueur.get(k), matchsDuJoueur.get(l));
							}
						}
					}
				}
			}
		return g;
	}
}

