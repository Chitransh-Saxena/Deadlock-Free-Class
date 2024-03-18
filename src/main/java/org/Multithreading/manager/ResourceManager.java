package org.Multithreading.manager;

import org.Multithreading.resource.Resource;

public interface ResourceManager {

    boolean allocateResource(Resource resource);
    void freeResource(Resource resource);
}
