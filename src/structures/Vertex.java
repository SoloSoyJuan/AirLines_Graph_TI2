package structures;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> implements IVertex<T> {
    //--------------------------------------------------------(Attributes)
    private T value;
    private List<IEdge<T>> edges;
    private boolean visited;
    //--------------------------------------------------------(Getters, Setters and Constructor)
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean getVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visited) {
    this.visited = visited;
    }

    @Override
    public List<IEdge<T>> getEdges() {
        return edges;
    }

    @Override
    public void setEdges(List<IEdge<T>> edges) {
        this.edges = edges;
    }

    /**
     *  Constructor of the class Vertex
     * @param value the value of the vertex
     */
    public Vertex(T value){
        this.value = value;
        this.visited = false;
        this.edges = new ArrayList<>();
    }
    //--------------------------------------------------------(Methods)


    @Override
    public void addConnection(IVertex<T> vertex, double weight) {
       edges.add(new Edge<>(vertex, weight));
    }


    @Override
    public boolean deleteConnection(IVertex<T> vertex) {
        boolean flat = false;
        int post = searchEdge(vertex.getValue());
        if(post<0){
            edges.remove(post);
            flat = true;
        }
        return flat;
    }

    /**
     * search an edge
     * @param value is the value of the vertex you search
     * @return the position in the edges were is the searching edge (returns -1 if does not exist)
     */
    public int searchEdge(T value){
        int post = -1;
        for (int i = 0; i< edges.size(); i++) {
            if(edges.get(i).getEnd().getValue().equals(value)){
                post = i;
                break;
            }
        }
        return post;
    }
}
