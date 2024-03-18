package org.Multithreading.allocation;

import org.Multithreading.resource.Resource;

public interface ResourceAllocation {

    boolean checkDeadlock();

    // Adds a request edge for a certain thread.
    // This represents a thread's intent to acquire a resource
    void addResourceRequest(Thread thread, Resource resource);


    // Adds an allocation edge to RAG.
    // This represents a resource being allocated to a graph
    void addResourceAllocation(Thread thread, Resource resource);


    void removeResourceRequest(Thread thread, Resource resource);

    void removeResourceAllocation(Thread thread, Resource resource);

}
