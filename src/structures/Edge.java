package structures;

public class Edge<T> implements IEdge<T> {
    //--------------------------------------------------------(Attributes)
    private double weight;
    private IVertex<T> end;
    //--------------------------------------------------------(Getters, Setters and Constructor)
    @Override
    public IVertex<T> getEnd() {
        return end;
    }

    @Override
    public void setEnd(IVertex<T> vertex) {
        this.end = vertex;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Constructor method of class Edge
     * @param end the adjacent vertex
     * @param weight the weight of the edge
     */
    public Edge(IVertex<T> end, double weight){
        this.end = end;
        this.weight = weight;
    }
    //--------------------------------------------------------(Methods)
}
