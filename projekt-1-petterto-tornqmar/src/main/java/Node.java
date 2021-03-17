import java.util.ArrayList;

public class Node {

    String name;
    Double latitude;
    Double longitude;
    ArrayList<Node> neighbours = new ArrayList<>();
    String id;
    //variable för calculateH för att få h värdet från current node till destination.
    double h;
    Node previous;

    public Node(String name, double v, double v1) {
        setName(name);
        setLatitude(v);
        setLongitude(v1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void addNeighbour(Node neighbour) {
        neighbours.add(neighbour);
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPrevious(Node previous){
        this.previous = previous;
    }

    public double calculateH(Node destination) {
        // Denna tar current (som vi redan är i) och sen tar destination noden och räknar ut distance.
        h = Utils.getDistance(longitude,
                latitude,
                destination.getLongitude(),
                destination.getLatitude());

        return h;

    }

    public double calculateG(Node source) {
        // denna tar current från innevarandenod ( från candidate listan i getRoute) och lägger G värde för dem.
        double g = 0;
        Node current = this;
        while (!(current == source)) {
                g = g + Utils.getDistance(current.longitude, current.latitude, previous.getLongitude(), previous.getLatitude());

                current = current.previous;
            }
        return g;
    }

    public double getF (double h, double g){
        // tar h och g värdet och lägger dem i F som används sen i getRoute (A*-algorytmen)
        double f;
        f = h + g;
        return f;
    }

}
