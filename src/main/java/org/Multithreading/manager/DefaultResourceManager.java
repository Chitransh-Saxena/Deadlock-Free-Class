package org.Multithreading.manager;

import org.Multithreading.allocation.ResourceAllocation;
import org.Multithreading.resource.Resource;

public class DefaultResourceManager implements ResourceManager {

    private ResourceAllocation resourceAllocationStrategy;

    public DefaultResourceManager(ResourceAllocation resourceAllocationStrategy) {
        this.resourceAllocationStrategy = resourceAllocationStrategy;
    }

    @Override
    public boolean allocateResource(Resource resource) {

        Thread thread = Thread.currentThread();
        resourceAllocationStrategy.addResourceRequest(thread, resource);

        if(resourceAllocationStrategy.checkDeadlock()) {

            resourceAllocationStrategy.removeResourceRequest(thread, resource);
            System.out.println("Deadlock Predicted: Resource allocation request denied");
            return false;
        }

        boolean allocated = resource.request();
        if(allocated) {
            resourceAllocationStrategy.addResourceAllocation(thread, resource);
        }
        else {
            resourceAllocationStrategy.removeResourceRequest(thread, resource);
        }

        return allocated;


    }

    @Override
    public void freeResource(Resource resource) {

        resource.release();
        resourceAllocationStrategy.removeResourceAllocation(Thread.currentThread(), resource);
    }
}
