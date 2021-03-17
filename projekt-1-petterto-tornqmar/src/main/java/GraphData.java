import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GraphData {
    // Flyttade upp denna ur creatGraph för att kunna instatiera getNodes.
    LinkedHashMap<String,Node> nodes = new LinkedHashMap<>();

    public ArrayList<Node> createGraph() {

        /**
         * Noderna instansieras med namn och koordinater i en
         * HashMap med en kortare sträng som nyckel (ID)
         *
         * LinkedHashMap är samma sak som HashMap, men dess element
         * förblir i sin ursprungliga ordning (en vanlig HashMap har ingen
         * garanterad ordning)
         */

        nodes.put("bole", new Node("Böle bibliotek",           60.2008, 24.9359));
        nodes.put("vall", new Node("Vallgårds bibliotek",      60.1923, 24.9626));
        nodes.put("berg", new Node("Berghälls bibliotek",      60.1837, 24.9536));
        nodes.put("tolo", new Node("Tölö bibliotek",           60.1833, 24.9175));
        nodes.put("oodi", new Node("Centrumbiblioteket Ode",   60.174,  24.9382));
        nodes.put("rich", new Node("Richardsgatans bibliotek", 60.1663, 24.9468));
        nodes.put("bush", new Node("Busholmens bibliotek",     60.16,   24.9209));
        
        /**
         * Data för nodernas grannar enligt linjerna på kartan. HashMap med ID
         * och en enkel String[]-array för grannarnas ID.
         */
        HashMap<String,String[]> neighbours = new HashMap<>();
        neighbours.put("bole", new String[]{"tolo", "berg"});
        neighbours.put("vall", new String[]{"berg"});
        neighbours.put("berg", new String[]{"bole", "vall", "tolo", "oodi"});
        neighbours.put("tolo", new String[]{"bole", "berg", "oodi", "bush"});
        neighbours.put("oodi", new String[]{"tolo", "berg", "rich"});
        neighbours.put("rich", new String[]{"oodi", "bush"});
        neighbours.put("bush", new String[]{"tolo", "rich"});

        /**
         *  Själva grafstrukturen kommer att sparas i följande ArrayList
         */
        ArrayList<Node> graph = new ArrayList<>();

        /**
         * Iterera noderna enligt ID och lägg till grannarna
         */
        for (String id : nodes.keySet()) {

            /* Ange nyckeln (t.ex. "bole") som ID åt noden, kan vara bra att ha för UI  */
         nodes.get(id).setId(id);


            /* Iterera nodens grannar och lägg till dem till noden */
            for (String neighbor : neighbours.get(id)) {
                nodes.get(id).addNeighbour(nodes.get(neighbor));
            }

            /* Lägg in noden i själva grafstrukturen */
            graph.add(nodes.get(id));
        }

        return graph;
    }
    // datatyp linkedHasMap, och returernar noderna i den.
    public LinkedHashMap<String,Node> getNodes (){
        return nodes;
    }

}
