package org.Multithreading.resource;

public class ConcreteResource implements Resource {

    private boolean isAvailable = true;

    @Override
    public synchronized boolean request() {
        if(isAvailable) {
            isAvailable = false;
            System.out.println("Resource requested and allocated successfully");
            return true;
        }
        else {
            System.out.println("Resource request failed: Resource already in use");
            return false;
        }
    }

    @Override
    public void release() {

        isAvailable = true;
        System.out.println("Resource has been released");
    }
}
