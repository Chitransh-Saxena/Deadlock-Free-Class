package org.Multithreading;

import org.Multithreading.allocation.ResourceAllocation;
import org.Multithreading.allocation.ResourceAllocationStrategy;
import org.Multithreading.manager.DefaultResourceManager;
import org.Multithreading.manager.ResourceManager;
import org.Multithreading.resource.ConcreteResource;
import org.Multithreading.resource.Resource;

public class Main {
    public static void main(String[] args) {

        // Initialisation
        ResourceAllocation resourceAllocation = new ResourceAllocationStrategy();
        ResourceManager resourceManager = new DefaultResourceManager(resourceAllocation);

        // Resource creation
        Resource resource1 = new ConcreteResource();
        Resource resource2 = new ConcreteResource();

        // Simulating threads requesting resources
        Thread thread1 = new Thread(() -> {
            boolean allocated = resourceManager.allocateResource(resource1);
            if(allocated) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            resourceManager.freeResource(resource1);
        });

        Thread thread2 = new Thread(() -> {
            boolean allocated = resourceManager.allocateResource(resource2);
            if(allocated) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            resourceManager.freeResource(resource2);
        });

        thread1.start();
        thread2.start();

    }
}