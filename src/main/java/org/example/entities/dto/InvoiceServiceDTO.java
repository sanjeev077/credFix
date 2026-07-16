package org.example.entities.dto;

import org.example.entities.Resource;
import org.example.enums.ServiceType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceDTO {
    public ServiceType serviceType;
    public List<InvoiceResourceDTO>resources;
    public BigDecimal serviceCost ;

    public InvoiceServiceDTO( ServiceType serviceType) {

        this.serviceType = serviceType;
        resources = new ArrayList<>();
        serviceCost = BigDecimal.ZERO;
    }



    public ServiceType getServiceType() {
        return serviceType;
    }

    public List<InvoiceResourceDTO> getResources() {
        return resources;
    }

    public void addResourceWithCost(Resource resource, BigDecimal cost)
    {
        InvoiceResourceDTO resourceDTO = new InvoiceResourceDTO(resource);
        resourceDTO.setResourceCost(cost);
        serviceCost = serviceCost.add(cost);
        resources.add(resourceDTO);
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }
}
