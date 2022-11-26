package structures;

import java.util.List;

public interface IGraph<T> {
    /**
     * return if the edges of the graph has weight
     * @return true if the edges has weight
     */
    public boolean isWeighted();

    /**
     * return if the graph is directed
     * @return true if the graph is directed
     */
    public boolean isDirected();

    /**
     * return the total of vertex in the graph
     * @return an int that indicate the total of vertex in the graph
     */
    public int totalVertex();

    /**
     * add a vertex in the graph
     * @param value value of the new vertex
     * @return true if the vertex was added
     */
    public boolean addVertex(T value);

    public List<List<IVertex<T>>> getGroups();

    /**
     * return a vertex you search if has the same value
     * @param value value that has to had the vertex you search
     * @return the vertex you search or null if doesn`t exist
     */
    public IVertex<T> getVertex(T value);

    /**
     * add an edge to a vertex
     * @param value1 value of the origin vertex, the ones the edge start
     * @param value2 value of the end vertex, the new abyasent of vertex1
     * @param weight a double that indicate the weight of the edge
     * @return true if the edge was added
     */
    public boolean addEdge(T value1, T value2, double weight);
    /**
     * set the attribute visited of all vertex in false
     */
    public void setVisited();
    /**
     * trigger dfs path on the graph
     */
    public void dfs();

    /**
     * trigger bfs path on the graph
     * * @param value the value of the vertex where the dfs is going to start
     *
     */
    public void bfs(T value);

    public boolean connected();

    public void dijkstra(T value);

    public Edge<T>[][] floydWarshall(T value);
    public void createdGroups();
    public void createOneGroup(T value);

    public void createTheFly(T value1, T value2);
}
