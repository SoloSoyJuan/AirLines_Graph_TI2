package structures;

import java.util.*;

public class ListGraph<T> implements IGraph<T> {
    //--------------------------------------------------------(Attributes)
    private  boolean weighted;
    private boolean directed;
    private int totalVertex;
    private List<IVertex<T>> graph;
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
    public List<List<IVertex<T>>> getGroups(){
        return groups;
    }

    @Override
    public boolean addVertex(T value) {
        boolean added = false;
        IVertex<T> vertex = getVertex(value);
        if (vertex == null) {
            graph.add(new Vertex<>(value));
            totalVertex++;
            added = true;
        }
        return added;
    }

    @Override
    public IVertex<T> getVertex(T value) {
        IVertex<T> vertex = null;
        for (IVertex<T> v: graph) {
            if(v.getValue().equals(value)){
                vertex = v;
                break;
            }
        }
        return vertex;
    }

    @Override
    public boolean addEdge(T value1, T value2, double weight) {
        boolean added = false;
        IVertex vet1 = getVertex(value1);
        IVertex vet2 = getVertex(value2);
        if(!weighted){
            weight = 0;
        }
        if (vet1 != null && vet2 != null) {
            vet1.addConnection(vet2, weight);
            if (!directed) {
                vet2.addConnection(vet1, weight);
            }
            added = true;
        }
        return added;
    }

    /**
     * Constructor of class ListGraph
     * @param weighted if graph edges has weight
     * @param directed if graph edges are directed
     */
    public ListGraph(boolean weighted, boolean directed){
        this.weighted = weighted;
        this.directed = directed;
        this.graph = new ArrayList<>();
        this.groups = new ArrayList<>();
    }
    //--------------------------------------------------------(Methods)

    @Override
    public void setVisited(){
        for (IVertex<T> v: graph) {
            v.setVisited(false);
            v.setParent(null);
            v.setDistance(Double.MAX_VALUE);
        }
    }
    @Override
    public void bfs(T value){
        IVertex<T> vert = getVertex(value);
        if(vert != null){
            bfs(vert);
        }
    }
    @Override
    public void dfs(){
        setVisited();
        for (IVertex<T> v: graph) {
            dfs(v);
        }
    }
    /**
     * bfs method, to see if is a strongly connected
     * @param root where the path start
     */
    private void bfs(IVertex<T> root){
        setVisited(); // setting the visited of all the vertex
        root.setVisited(true); // setting the visited of the root
        Queue<IVertex<T>> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){ // while the queue isn´t empty continue
            IVertex<T> current = Q.poll();
            for (IEdge<T> e: current.getEdges()) { // checking the adjacent vertex
                if(!e.getEnd().getVisited()){ // if the vertex has not been visited
                    e.getEnd().setVisited(true);
                    Q.add(e.getEnd());
                }
            }
        }
    }
    /**
     * dfs method, to see if is a strongly connected
     * @param current vertex you are checking
     */
    private void dfs(IVertex<T> current){
        current.setVisited(true);
        for (IEdge<T> e: current.getEdges()) {
            if (!e.getEnd().getVisited()) {
                e.getEnd().setParent(current);
                dfs(e.getEnd());
            }
        }
    }

    public boolean connected(){
        boolean connected = true;
        dfs();
        int a = 0;
        for (IVertex<T> v: graph) {
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
        IVertex<T> vert = getVertex(value);
        vert.setDistance(0);
        PriorityQueue<IVertex<T>> pq = new PriorityQueue<>();
        pq.add(vert);
        while(!pq.isEmpty()){
            IVertex<T> current = pq.poll();
            for (IEdge<T> e: current.getEdges()) {
                double alt = current.getDistance() + e.getWeight();
                if(alt < e.getEnd().getDistance()){
                    e.getEnd().setDistance(alt);
                    e.getEnd().setParent(current);
                    pq.add(e.getEnd());
                }
            }
        }
    }

    @Override
    public Edge<T>[][] floydWarshall(T value){
        return null;
    }

    @Override
    public void createdGroups(){
        groups = new ArrayList<>();
        for (IVertex<T> v: graph) {
            ArrayList<IVertex<T>> info = new ArrayList<>();
            dfs();
            if(v.getParent()==null){
                bfs(v);
                for (IVertex<T> e: graph) {
                    if (e.getVisited()) {
                        info.add(e);
                    }
                }
            }
            if(!info.isEmpty()){
               groups.add(info);
            }
        }
    }

    @Override
    public void createOneGroup(T value){
        groups = new ArrayList<>();
        IVertex<T> vert = getVertex(value);
        bfs(vert);
        ArrayList<IVertex<T>> theGroup = new ArrayList<>();
        for (IVertex<T> v: graph) {
            if (v.getVisited()) {
                theGroup.add(v);
            }
        }
        groups.add(theGroup);
    }
}
