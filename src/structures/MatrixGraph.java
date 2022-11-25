package structures;

import java.util.*;

public class MatrixGraph<T> implements IGraph<T> {
    //--------------------------------------------------------(Attributes)
    private  boolean weighted;
    private boolean directed;
    private int totalVertex;
    private IEdge<T> [][] graph;
    private List<IVertex<T>> vertex;

    private List<List<IVertex<T>>> groups;
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
    public List<List<IVertex<T>>> getGroups() {
        return groups;
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

    public int getPostVertex(T value) {
        int post = -1;
        for (int v = 0; v < vertex.size(); v++) {
            if(vertex.get(v).getValue().equals(value)){
                post = v;
                break;
            }
        }
        return post;
    }

    @Override
    public boolean addEdge(T value1, T value2, double weight) {
        boolean added = false;
        int vet1 = getPostVertex(value1);
        int vet2 = getPostVertex(value2);
        if(vet1 != -1 && vet2 != -1){
            graph[vet1][vet2] = new Edge<>(getVertex(value2),weight);
            if(!directed){
                graph[vet2][vet1] = new Edge<>(getVertex(value1), weight);
            }
            added = true;
        }
        return added;
    }

    /**
     * Constructor of class MatrixGraph
     * @param weighted if graph edges has weight
     * @param directed if graph edges are directed
     * @param totalVertex number of vertex
     */
    public MatrixGraph(boolean weighted, boolean directed, int totalVertex){
        this.weighted = weighted;
        this.directed = directed;
        this.totalVertex = totalVertex;
        this.graph = (IEdge<T>[][]) new Edge<?>[totalVertex][totalVertex];
        this.vertex = new ArrayList<>();
    }
    //--------------------------------------------------------(Methods)
    @Override
    public void setVisited(){
        for (IVertex<T> v: vertex) {
            v.setVisited(false);
            v.setParent(null);
            v.setDistance(Double.MAX_VALUE);
        }
    }
    @Override
    public void dfs() {
        setVisited();
        for(int i = 0; i < vertex.size(); i++){
            dfs(i);
        }
    }
    /**
     * dfs method, to see if is a strongly connected
     * @param current vertex you are checking
     */
    private void dfs(int current){
        vertex.get(current).setVisited(true);
        for (int i = 0; i < graph.length; i++) {
            if(graph[current][i].getWeight() != 0){
                vertex.get(i).setParent(vertex.get(current));
                dfs(i);
            }
        }
    }

    @Override
    public void bfs(T value) {
        setVisited();
        int root = getPostVertex(value);
        vertex.get(root).setVisited(true);
        Queue<Integer> Q = new LinkedList<>();
        Q.add(root);
        while (!Q.isEmpty()){
            int current = Q.poll();
            for (int i = 0; i < graph.length; i++) {
                if (graph[current][i].getWeight() != 0) {
                    if(!vertex.get(i).getVisited()){
                        vertex.get(i).setVisited(true);
                        Q.add(i);
                    }
                }
            }
        }
    }

    @Override
    public boolean connected() {
        boolean connected = true;
        dfs();
        int a = 0;
        for (IVertex<T> v:vertex) {
            if(v.getParent() == null){
                a++;
            }
        }
        if (a>1){
            connected = false;
        }
        return connected;
    }

    @Override
    public void dijkstra(T value){
        setVisited();
        int vert = getPostVertex(value);
        PriorityQueue<IVertex<T>> pq = new PriorityQueue<>();
        pq.add(vertex.get(vert));
        while(!pq.isEmpty()){

        }
    }

    @Override
    public Edge<T>[][] floydWarshall(T value){
        setVisited();
        Edge<T>[][] secGraph = (Edge<T>[][]) graph.clone();

        for (int i = 0; i < vertex.size(); i++) {
            for (int j = 0; j < vertex.size(); j++) {
                for (int k = 0; k < vertex.size(); k++) {
                    double alt = secGraph[j][i].getWeight()+secGraph[i][k].getWeight();
                    if(secGraph[j][k].getWeight()>alt){
                        secGraph[j][k] = new Edge<>(alt);
                    }
                }
            }
        }
        return secGraph;
    }

    @Override
    public void createdGroups(){
        dfs();

    }
}
