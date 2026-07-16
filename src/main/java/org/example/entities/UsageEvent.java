package org.example.entities;

import org.example.enums.ServiceType;

import java.math.BigDecimal;
import java.util.UUID;

public class UsageEvent {
    String eventId;
    String userId;
    String resourceId;
    ServiceType serviceType;
    BigDecimal quantity;
    long timeStamp;
    String unit;

    public UsageEvent(String userId, String resourceId, ServiceType serviceType, BigDecimal quantity, long timeStamp, String unit) {
        this.eventId = UUID.randomUUID().toString();
        this.userId = userId;
        this.resourceId = resourceId;
        this.serviceType = serviceType;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
        this.unit=unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }



    public long getTimeStamp() {
        return timeStamp;
    }
}
