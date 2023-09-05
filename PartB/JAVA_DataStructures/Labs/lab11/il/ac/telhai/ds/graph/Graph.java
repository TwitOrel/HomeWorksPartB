package lab11.il.ac.telhai.ds.graph;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {

    private class Edge {
        private final V u;
        private final V v;
        private E label;

        public Edge(V v, V u, E label) {
            this.label = label;
            this.u = u;
            this.v = v;
        }

        private E setLabel(E other) {
            this.label = other;
            return other;
        }
    }

    // Map to store vertices and their incident edges
    private final Map<V, List<Edge>> vertexToIncidences = new TreeMap<>();

    public Graph() {
    }

    @Override
    public void add(V v) {
        if (containsVertex(v)) {
            return;
        }
        List<Edge> list = new LinkedList<>();
        vertexToIncidences.put(v, list);
    }

    @Override
    public E getEdge(V u, V v) {
        Edge edge = getEdgeV(u, v);
        if (edge == null) {
            return null;
        }
        return edge.label;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        List<Edge> listU = new LinkedList<>();
        this.vertexToIncidences.putIfAbsent(u, listU);
        List<Edge> listV = new LinkedList<>();
        this.vertexToIncidences.putIfAbsent(v, listV);

        //check if the bridge exist
        Edge edge = getEdgeV(u, v);
        if (edge == null) {
            edge = new Edge(v, u, edgeLabel);
            this.vertexToIncidences.get(u).add(edge);
            this.vertexToIncidences.get(v).add(edge);
        } else {
            edge.setLabel(edgeLabel);
            Edge edge1 = getEdgeV(v, u);
            assert edge1 != null;
            edge1.setLabel(edgeLabel);
        }
        return edgeLabel;
    }

    @Override
    public boolean containsVertex(V v) {
        return vertexToIncidences.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        List<Edge> list = this.vertexToIncidences.get(v);
        for (Edge edge : list) {
            removeEdge(edge.u, edge.v);
        }
        this.vertexToIncidences.remove(v);
    }


    @Override
    public E removeEdge(V u, V v) {
        if (findEdgeV(u, v) == -1) {
            return null;
        }
        Edge e = getEdgeV(u, v);
        this.vertexToIncidences.get(u).remove(e);
        this.vertexToIncidences.get(v).remove(e);
        assert e != null;
        return e.label;
    }

    //Finds the minimum distance between two vertices in the graph.
    @Override
    public double getWeight(V u, V v) {
        Edge edge = getEdgeV(u, v);
        if (edge == null) {
            return 0;
        }
        if (edge.label instanceof Number w) {
            return w.doubleValue();
        } else if (edge.label instanceof Weighted w) {
            return w.getWeight();
        }
        throw new RuntimeException("no exist");
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Set<V> set = this.vertexToIncidences.keySet();
        for (V v : set) {
            ret.append(v.toString()).append(",");
        }
        if (ret.length() > 1) {
            ret.deleteCharAt(ret.length() - 1);
        }
        return ret.toString();
    }

    @Override
    public String toStringExtended() {
        StringBuilder ret = new StringBuilder();
        Set<V> set = this.vertexToIncidences.keySet();
        for (V v : set) {
            ret.append(v.toString()).append(":");
            List<Edge> list = vertexToIncidences.get(v);
            int x = 0;
            for (Edge edge : list) {
                if (x != 0) {
                    ret.append(",");
                }
                ret.append("{").append(edge.u.toString()).append(",").append(edge.v.toString()).append("}");
                if (edge.label instanceof Weighted) {
                    ret.append("(");
                    ret.append(((Weighted) edge.label).getWeight());
                    ret.append(")");
                } else if (edge.label instanceof Number) {
                    ret.append("(");
                    ret.append(edge.label);
                    ret.append(")");
                }
                x++;

            }
            ret.append("\n");
        }
        if (ret.length() > 1) {
            ret.deleteCharAt(ret.length() - 1);
        }
        return ret.toString();
    }

    // Checks if two vertices are adjacent (connected by an edge).
    @Override
    public boolean areAdjacent(V u, V v) {
        int res;
        try {
            res = findEdgeV(u, v);
        } catch (RuntimeException e) {
            return false;
        }
        return res != -1;
    }

    private int findEdgeV(V u, V v) {
        if (!containsVertex(u) || !containsVertex(v)) {
            throw new RuntimeException("the edge canot be exist less one of the road");
        }
        List<Edge> list = vertexToIncidences.get(v);
        int count = 0;
        if (list == null) {
            return -1;
        }
        for (Edge edge : list) {
            if (edge.v.equals(v) && edge.u.equals(u) || edge.v.equals(u) && edge.u.equals(v)) {
                return count;
            } else {
                count++;
            }
        }
        return -1;
    }

    private Edge getEdgeV(V u, V v) {
        List<Edge> list = vertexToIncidences.get(v);
        if (list == null) {
            return null;
        }
        for (Edge edge : list) {
            if ((edge.v == v && edge.u == u) || (edge.v == u && edge.u == v)) {
                return edge;
            }
        }
        return null;
    }

    //  lab 12

    public List<V> bfs(V source) {
        List<V> list = new ArrayList<>();
        Set<V> set = new HashSet<>();
        Queue<V> tor = new LinkedList<>();

        tor.offer(source);
        set.add(source);
        while (!tor.isEmpty()) {
            V tmp = tor.poll();
            list.add(tmp);
            List<Edge> neighbor = this.vertexToIncidences.get(tmp);
            for (Edge e : neighbor) {
                if (!set.contains(e.u)) {
                    tor.offer(e.u);
                    set.add(e.u);

                }
                if (!set.contains(e.v)) {
                    tor.offer(e.v);
                    set.add(e.v);

                }
            }
        }
        return list;
    }

    private void dfs_visit(List<V> list, Set<V> set, V v) {
        set.add(v);
        list.add(v);
        for (Edge e : this.vertexToIncidences.get(v)) {
            if (!set.contains(e.u)) {
                dfs_visit(list, set, e.u);
            }
            if (!set.contains(e.v)) {
                dfs_visit(list, set, e.v);
            }
        }
    }

    public List<V> dfs() {
        List<V> list = new ArrayList<>();
        Set<V> set = new HashSet<>();

        for (V v : this.vertexToIncidences.keySet()) {
            if (!set.contains(v)) {
                dfs_visit(list, set, v);
            }
        }
        return list;
    }

    //  lab 13
    public Map<V, Double> distancesFrom(V vertex) {
        class ExtendVertex implements Comparable<ExtendVertex> {
            private final Double weight;
            private final V vertex;

            public ExtendVertex(V vertex, Double weight) {
                this.vertex = vertex;
                this.weight = weight;
            }

            @Override
            public int compareTo(ExtendVertex o) {
                return this.weight.compareTo(o.weight);
            }
        }

        // Initialize the containers
        Map<V, Double> distances = new TreeMap<>();
        Queue<ExtendVertex> priorityQueue = new PriorityQueue<>();
        Set<V> set = new HashSet<>();

        // Set the initial distances
        for (V thisVertex : vertexToIncidences.keySet()) {
            distances.put(thisVertex, Double.POSITIVE_INFINITY);
        }

        // Set the source vertex distance to 0
        distances.put(vertex, 0.0);
        ExtendVertex sourceVertex = new ExtendVertex(vertex, distances.get(vertex));
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            ExtendVertex currentVertex = priorityQueue.remove();
            set.add(currentVertex.vertex);

            for (Edge edge : vertexToIncidences.get(currentVertex.vertex)) {
                V adjacentVertex = edge.u.equals(currentVertex.vertex) ? edge.v : edge.u;

                if (!set.contains(adjacentVertex)) {
                    double weight = (edge.label instanceof Weighted) ? ((Weighted) edge.label).getWeight() : (Double) edge.label;
                    double newWeight = currentVertex.weight + weight;

                    if (newWeight < distances.get(adjacentVertex)) {
                        distances.put(adjacentVertex, newWeight);
                        ExtendVertex adjacentExtendVertex = new ExtendVertex(adjacentVertex, newWeight);
                        priorityQueue.add(adjacentExtendVertex);
                    }
                }
            }
        }

        // Remove vertices with infinite weights
        Set<V> verticesToRemove = new HashSet<>();
        for (V vertexToRemove : distances.keySet()) {
            if (distances.get(vertexToRemove).equals(Double.POSITIVE_INFINITY)) {
                verticesToRemove.add(vertexToRemove);
            }
        }
        for (V vertexToRemove : verticesToRemove) {
            distances.remove(vertexToRemove);
        }

        return distances;
    }


}
