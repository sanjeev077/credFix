package org.example.repositories;

import org.example.entities.Service;
import org.example.entities.UsageEvent;
import org.example.entities.User;
import org.example.enums.ServiceType;
import org.example.exceptions.UserNotFoundException;

import java.util.List;

public interface UsageEventRepository {
    public List<UsageEvent> getAllUsage(String userId,long startTime , long endTime );

    public User getUser(String userId) throws UserNotFoundException;

    public void addUsageEvent(UsageEvent usageEvent); // check event duplicate first

    public User addUser(User user);//check user duplicate first before putting in DB

    public Service getService(ServiceType serviceType);

    public Service addService(Service service);
}
