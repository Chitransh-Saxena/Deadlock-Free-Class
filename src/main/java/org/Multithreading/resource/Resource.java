package org.Multithreading.resource;

public interface Resource {

    boolean request();
    void release();
}
