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
			// Lecture du fichier texte et Extration des identifiants des joueurs
			List<Integer> joueursIds = new ArrayList<>();
			try {
				// parcours chaque ligne et enl�ve les 'J-' puis m�morise leurs ids
				for (String ligne : Files.readAllLines(Paths.get(path))) {
					 for (String chaine : ligne.split(" ")){
						 if(chaine.length()>2) {
							 String chaineFiltered = chaine.replace("J-","");
							 int joueurId= Integer.parseInt(chaineFiltered);
							 //System.out.println("id du joueur " + joueurId);
							 joueursIds.add(joueurId);
						 }
					 }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<joueursIds.size();i++) {
				if(joueurs.contains(joueursIds.get(i))==false) {
					joueurs.add(joueursIds.get(i));
					matchsDuJoueur.clear();
					// M�morisation des matchs du joueur
					for(int j=i;j<joueursIds.size();j++) {
						if(joueursIds.get(j).equals(joueursIds.get(i))) {
							matchsDuJoueur.add(j/10);
						}
					}
					System.out.println("Matchs du joueur "+joueursIds.get(i)+" : "+matchsDuJoueur);
					// Creation du graph
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
				if(g.vertices().contains(i/10)==false) {
					g.addVertex(i/10);
				}
			}
			System.out.println(g.vertices().size());
		return g;
	}
}

