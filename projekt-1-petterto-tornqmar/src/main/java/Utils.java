import java.util.ArrayList;

public class Utils {

    /**
     * Metod för att beräkna distansen mellan två geografiska koordinater
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        lon1 = lon1 * Math.PI / 180.0;
        lat1 = lat1 * Math.PI / 180.0;
        lon2 = lon2 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double km = 6367 * c;

        return km;
    }

    public static void showNodesAndLinks(ArrayList<Node> graphData){
    // Metod som skriver ut LinkedHasmapen med dens innehåll. samt relations(grannar)
        for (int i = 0; i < graphData.size(); i++) {

            System.out.println("[" + graphData.get(i).getId() + "] " + graphData.get(i).getName() + " granne är: ");

            for (int j = 0; j < graphData.get(i).getNeighbours().size(); j++) {

                System.out.println(graphData.get(i).getNeighbours().get(j).getName());
            }
        }

    }
// A* algo, som gör "smarta" val av den snabbaste rutten ( kolla kortaste F värdet)
    public static ArrayList<Node> getRoute(Node source, Node destination) {
        ArrayList<Node> candidates = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node current = source;
        boolean done = false;

        while (!done) {
            double minF = 0;
            Node next = null;

            for (int i = 0; i < current.getNeighbours().size(); i++) {
                // Kollar att grannen inte finns i candidates eller i visited
                // Ifall den inte finns så sätter den till den i candidates listan
                Node neighbour = current.getNeighbours().get(i);
                if (!candidates.contains(neighbour) && !visited.contains(neighbour)){
                    candidates.add(current.getNeighbours().get(i));
                    neighbour.setPrevious(current);
                }
            }

            // Loopar igenom candidates beroende på dess storlek
            for (int i = 0; i < candidates.size(); i++) {
                if (candidates.get(i) == destination){
                    done = true;
                    break;
                } else {
                    double g = candidates.get(i).calculateG(source);
                    double h = candidates.get(i).calculateH(destination);
                    double f = candidates.get(i).getF(h, g );
                    if(minF == 0 || minF > f){

                        minF = f;
                        next = candidates.get(i);
                    }
                }
            }

            //
            if (!done) {
                current = next;
                visited.add(current);
                candidates.remove(current);
            }
        }
        ArrayList<Node> route = new ArrayList<>();
        current = destination;

        while (!(current == source)) {
            route.add(current);
            current = current.previous;
            //testing
        }
        System.out.println(current.getName());
        for (int i = route.size()-1; i >= 0; i--){

            System.out.println(route.get(i).getName());
        }

        return route;

    }
}
