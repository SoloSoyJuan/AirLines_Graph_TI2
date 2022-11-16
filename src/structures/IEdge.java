package structures;

public interface IEdge<T> {
    /**
     * get method of Attribute end
     * @return adjacent vertex
     */
    public IVertex<T> getEnd();

    /**
     * set method of Attribute end
     * @param vertex adjacent vertex
     */
    public void setEnd(IVertex<T> vertex);

    /**
     * get method of Attribute weight
     * @return the weight of the edge
     */
    public double getWeight();

    /**
     * set method of Attribute weight
     * @param weight weight of the edge
     */
    public void setWeight(double weight);
}
