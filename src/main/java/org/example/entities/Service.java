package org.example.entities;

import org.example.enums.ServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Service {

    ServiceType serviceType;
    List<Resource> resourceList;

    public Service(ServiceType serviceType) {
        this.serviceType = serviceType;
        resourceList=new ArrayList<>();
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void addResource(Resource resource) {
        this.resourceList.add(resource);
    }


}
