package it.unibo.generics.graph.api;

import it.unibo.generics.graph.api.Graph;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphImpl<N> implements Graph<N>{

    private final Map<N, Set<N>> graph = new HashMap<>();
    /*NON  CAPITO*/
    private final FringeAccumulationStrategy<Step<N>> strategy;

    @Override
    public void addNode(final N node) {
        graph.put(node, new HashSet<N>());
    }

    @Override
    public void addEdge(final N source, final N target) {
        if (!graph.containsKey(source)) {
            addNode(source);
        }
        if (!graph.containsKey(target)) {
            addNode(target);
        }
        graph.get(source).add((N) target);
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(graph.keySet());
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return graph.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        if (nodeExitst(source, target)) {
            return graphSearch(source, target);
        } else {
            return Collections.emptyList();
        }
    }
    @SafeVarargs
    private boolean nodeExitst(final N... nodes) {
        for (final N node : nodes) {
            if (!graph.containsKey(node)) {
                return false;
            }
        }
        return true;
    }
    /*NON  CAPITO*/
    private List<N> graphSearch(final N source, final N target) {
        final Deque<Step<N>> fringe = new LinkedList<>();
        fringe.add(new Step<>(source));
        final Set<N> alreadyVisited = new HashSet<>();
        while (!fringe.isEmpty() && alreadyVisited.size() < getNodesCount()) {
            final Step<N> lastStep = fringe.removeFirst();
            final N currentNode = lastStep.getPosition();
            if (currentNode.equals(target)) {
                return lastStep.getPath();
            } else if (!alreadyVisited.contains(currentNode)) {
                alreadyVisited.add(currentNode);
                updateFringe(fringe, lastStep);
            }
        }
        return Collections.emptyList(); 
    }
    /*NON  CAPITO*/
    private int getNodesCount() {
        return graph.keySet().size();
    }
    /*NON  CAPITO*/
    private void updateFringe(final Deque<Step<N>> fringe, final Step<N> lastStep) {
        final N currentNode = lastStep.getPosition();
        for (final N reachableNode : linkedNodes(currentNode)) {
            strategy.addToFringe(fringe, new Step<>(lastStep, reachableNode));
        }
    }
}
