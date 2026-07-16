package org.example.entities;

import org.example.entities.pricing.PricingModel;
import org.example.enums.ResourceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public  class Resource {

    private final PricingModel pricingModel;
    String resourceId;
    ResourceType resourceType;

    public Resource(PricingModel pricingModel, ResourceType resourceType)
    {
        this.resourceType = resourceType;
        this.pricingModel = pricingModel;
        this.resourceId = UUID.randomUUID().toString();
    }

    public PricingModel getPricingModel() {
        return pricingModel;
    }

    public String getResourceId() {
        return resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public BigDecimal computeCost(List<UsageEvent>usageEventList)
    {
        List<UsageEvent>filterList = usageEventList.stream().filter(k->k.getResourceId().equals(resourceId)).toList();
        return pricingModel.computeBill(filterList);
    }
}
