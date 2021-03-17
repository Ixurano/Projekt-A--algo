import java.util.ArrayList;
import java.util.Scanner;


/**
 * Projekt 1 - ruttsökning
 *
 * Datastrukturer och algoritmer 2021
 *
 * Programmeringsteam:
 *
 */
public class Main {


	public static void main(String[] args) {
		Scanner scanOjbect = new Scanner(System.in);
		// skapar en ny GraphData
		GraphData graphData = new GraphData();
		// Skapar ny ArrayList för programmet som createGraph.
		ArrayList<Node> graph = graphData.createGraph();
		//listar alla bibiliotek och dens grannar
		Utils.showNodesAndLinks(graph);
		System.out.println("\n");

		//Kör startpunkt samt destination för A*-algoritmen
		System.out.println("Vart startar du ifrån [använd ID]\n");
		String currentUser = scanOjbect.nextLine();
		System.out.println("Vart är destionationen?[använd ID]\n");
		String destinationUser = scanOjbect.nextLine();
		Node source = graphData.getNodes().get(currentUser);
		Node destination = graphData.getNodes().get(destinationUser);
		System.out.println("Kortaste rutten är: ");
		Utils.getRoute(source, destination);
	}
}

