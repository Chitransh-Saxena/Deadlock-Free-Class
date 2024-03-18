package org.Multithreading.allocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResourceAllocationGraph {

    private final Map<Integer, Set<Integer>> adjList = new HashMap<>();

    public void addEdge(int from, int to) {

        // This method checks if from node is absent, if yes, then adds new hashset to it. At the end, adds node to it.
        // If node already exists in value set, no issue, this is a set.
        adjList.computeIfAbsent(from, k -> new HashSet<>()).add(to);
    }

    public void removeEdge(int from, int to) {

        adjList.computeIfPresent(from, (key, edges) -> {
            edges.remove(to);
            return edges.isEmpty() ? null : edges;
        });
    }

    public boolean hasCycle() {

        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for(Integer node: adjList.keySet()) {

            if(detectCycle(node, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean detectCycle(Integer node, Set<Integer> visited, Set<Integer> recursionStack) {

        if(recursionStack.contains(node))  return true;

        if(visited.add(node)) {

            recursionStack.add(node);
            Set<Integer> children = adjList.get(node);
            if(children != null) {
                for(Integer child: children) {
                    if(detectCycle(child, visited,recursionStack)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
