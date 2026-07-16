package org.example.entities.dto;

import org.example.entities.Resource;
import org.example.enums.ResourceType;

import java.math.BigDecimal;

public class InvoiceResourceDTO {
    String resourceId;
    ResourceType resourceType;
    BigDecimal resourceCost;

    public InvoiceResourceDTO(Resource resource) {
        resourceId = resource.getResourceId();
        resourceType = resource.getResourceType();
    }
    public void setResourceCost(BigDecimal resourceCost)
    {
        this.resourceCost = resourceCost;
    }

    public String getResourceId() {
        return resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public BigDecimal getResourceCost() {
        return resourceCost;
    }
}
