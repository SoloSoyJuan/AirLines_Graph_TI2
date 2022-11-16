package structures;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<T> implements IGraph<T> {
    //--------------------------------------------------------(Attributes)
    private  boolean weighted;
    private boolean directed;
    private int totalVertex;
    private IEdge<T> [][] graph;
    private List<IVertex<T>> vertex;
    //--------------------------------------------------------(Getters, Setters and Constructor)
    @Override
    public boolean isWeighted() {
        return weighted;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public int totalVertex() {
        return totalVertex;
    }

    @Override
    public boolean addVertex(T value) {
        boolean added = false;
        IVertex<T> vert = getVertex(value);
        if (vert == null) {
            vertex.add(new Vertex<>(value));
            totalVertex++;
            added = true;
        }
        return added;
    }

    @Override
    public IVertex<T> getVertex(T value) {
        IVertex<T> vert = null;
        for (IVertex<T> v: vertex) {
            if(v.getValue().equals(value)){
                vert = v;
                break;
            }
        }
        return vert;
    }

    @Override
    public boolean addEdge(T value1, T value2, double weight) {
        return false;
    }

    /**
     * Constructor of class MatrixGraph
     * @param weighted if graph edges has weight
     * @param directed if graph edges are directed
     */
    public MatrixGraph(boolean weighted, boolean directed){
        this.weighted = weighted;
        this.directed = directed;
        this.totalVertex = 50;
        this.graph = (IEdge<T>[][]) new Edge<?>[totalVertex][totalVertex];
        this.vertex = new ArrayList<>();
    }
    //--------------------------------------------------------(Methods)
    @Override
    public void dfs() {

    }

    @Override
    public void bfs(T value) {

    }

    @Override
    public boolean connected() {
        return false;
    }
}
