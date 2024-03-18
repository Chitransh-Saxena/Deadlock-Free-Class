package org.Multithreading.allocation;

import org.Multithreading.resource.Resource;

public class ResourceAllocationStrategy implements ResourceAllocation {

    private final ResourceAllocationGraph rag = new ResourceAllocationGraph();


    private int getThreadId(Thread thread) { return System.identityHashCode(thread); }
    private int getResourceId(Resource resource) { return System.identityHashCode(resource); }


    @Override
    public synchronized boolean checkDeadlock() {
        return rag.hasCycle();
    }

    @Override
    public synchronized void addResourceRequest(Thread thread, Resource resource) {
        rag.addEdge(getThreadId(thread), getResourceId(resource));
        System.out.println("Resource request added to allocation graph");
    }

    @Override
    public synchronized void addResourceAllocation(Thread thread, Resource resource) {

        rag.addEdge(getResourceId(resource), getThreadId(thread));
        System.out.println("Resource allocation added to allocation graph.");
    }

    @Override
    public synchronized void removeResourceRequest(Thread thread, Resource resource) {
        rag.removeEdge(getThreadId(thread), getResourceId(resource));
        System.out.println("Resource request removed from allocation graph.");
    }

    @Override
    public synchronized void removeResourceAllocation(Thread thread, Resource resource) {
        rag.removeEdge(getResourceId(resource), getThreadId(thread));
        System.out.println("Resource allocation removed from allocation graph.");
    }
}
