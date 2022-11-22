package structures;

import java.util.List;

public interface IVertex<T> {
    /**
     * get method of Attribute value
     * @return the value of the vertex
     */
    public T getValue();

    /**
     * set method of attribute value
     * @param value the value of the vertex
     */
    public void setValue(T value);

    /**
     * get method of Attribute visited
     * @return true if the vertex has been visited
     */
    public boolean getVisited();

    /**
     * set method of Attribute visited
     * @param visited true if vertex has been visited
     */
    public void setVisited(boolean visited);

    /**
     * get method of de Attribute edges
     * @return a List with all the edges of the vertex
     */
    public List<IEdge<T>> getEdges();

    /**
     * set method of the Attribute edges
     * @param edges a List with all the edges of the vertex
     */
    public void setEdges(List<IEdge<T>> edges);

    public IVertex<T> getParent();

    public void setParent(IVertex<T> parent);

    /**
     * add a new edge
     * @param vertex the adjacent vertex
     * @param weight the weight of the edge
     */
    public void addConnection(IVertex<T> vertex, double weight);
    /**
     * delete an edge
     * @param vertex adjacent vertex
     * @return true of the edge exist and was deleted
     */
    public boolean deleteConnection(IVertex<T> vertex);
}
