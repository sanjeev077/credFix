package org.example.entities;

import org.example.enums.ServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    String uid;
    String name;
    List<ServiceType>subscribedServices;
    public User(String name)
    {
        uid = UUID.randomUUID().toString();
        this.name = name;
        this.subscribedServices = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public List<ServiceType> getSubscribedServices() {
        return subscribedServices;
    }

    public void setSubscribedServices(List<ServiceType> subscribedServices) {
        this.subscribedServices = subscribedServices;
    }

    public void subscribeToService(ServiceType serviceType)
    {
        subscribedServices.add(serviceType);
    }
}
