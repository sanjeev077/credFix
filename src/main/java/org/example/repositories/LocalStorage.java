package org.example.repositories;

import org.example.entities.Service;
import org.example.entities.UsageEvent;
import org.example.entities.User;
import org.example.enums.ServiceType;
import org.example.exceptions.ServiceNotAvailable;
import org.example.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalStorage implements UsageEventRepository{

    List<UsageEvent>usageEventList;
    Map<String,User> userMap;
    private final Map<ServiceType, Service> serviceMap;

    public LocalStorage()
    {
        usageEventList = new ArrayList<>();
        userMap= new HashMap<>();
        serviceMap = new HashMap<>();
    }

    @Override
    public List<UsageEvent> getAllUsage(String userId, long startTime, long endTime) {

        User user = getUser(userId);
        return usageEventList.stream().filter(k->k.getUserId().equals(userId))
                .filter(k->(k.getTimeStamp()>=startTime && k.getTimeStamp()<endTime)).toList();
    }

    @Override
    public User getUser(String userId) throws UserNotFoundException {
        if(userMap.containsKey(userId))
        {
            return userMap.get(userId);
        }
        throw new UserNotFoundException("No user found with UserId "+userId);
    }

    @Override
    public void addUsageEvent(UsageEvent usageEvent) {
       usageEventList.add(usageEvent);
    }

    @Override
    public User addUser(User user) {
       userMap.put(user.getUid(),user);
       return userMap.get(user.getUid());
    }

    @Override
    public Service getService(ServiceType serviceType) {
        if(!serviceMap.containsKey(serviceType))
        {
            throw new ServiceNotAvailable("service with given service type not found ,Service type provided "+serviceType);
        }
        return serviceMap.get(serviceType);
    }

    @Override
    public Service addService(Service service) {
        return serviceMap.put(service.getServiceType(), service);
    }
}
