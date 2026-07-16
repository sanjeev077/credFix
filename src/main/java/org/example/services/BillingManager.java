package org.example.services;

import org.example.entities.*;
import org.example.entities.dto.InvoiceResourceDTO;
import org.example.entities.dto.InvoiceServiceDTO;
import org.example.enums.ServiceType;
import org.example.repositories.UsageEventRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingManager {

    private static volatile  BillingManager billingManager;

    UsageEventRepository usageEventRepository;
    private BillingManager(UsageEventRepository usageEventRepository)
    {
        this.usageEventRepository = usageEventRepository;
    }

    public static BillingManager getBillingManager(UsageEventRepository usageEventRepository)
    {
        if(billingManager==null)
        {
            synchronized (BillingManager.class)
            {
                if (billingManager==null)
                {
                    billingManager = new BillingManager(usageEventRepository);
                }
            }
        }

        return billingManager;
    }

    public User addUser(User user)
    {
        return usageEventRepository.addUser(user);
    }
    public void addUsageEvent(UsageEvent usageEvent)
    {
        usageEventRepository.addUsageEvent(usageEvent);
    }



    public Invoice generateInvoice(String userId, long startTime, long endtime)
    {
        User user = usageEventRepository.getUser(userId);
        List<UsageEvent> usageEventList = usageEventRepository.getAllUsage(userId, startTime, endtime);

        Invoice invoice = new Invoice(startTime,endtime);
        invoice.setUserName(user.getName());


        Map<ServiceType, List<UsageEvent>> usageByService = usageEventList.stream()
                .collect(Collectors.groupingBy(UsageEvent::getServiceType));

        for (Map.Entry<ServiceType, List<UsageEvent>> entry : usageByService.entrySet())
        {
            Service service = usageEventRepository.getService(entry.getKey());
            InvoiceServiceDTO invoiceServiceDTO = new InvoiceServiceDTO(service.getServiceType());

            for (Resource resource : service.getResourceList())
            {
                BigDecimal resourceCost = resource.computeCost(entry.getValue());
                if (resourceCost.compareTo(BigDecimal.ZERO) > 0) {
                    invoiceServiceDTO.addResourceWithCost(resource, resourceCost);
                }
            }
            invoice.addService(invoiceServiceDTO);
        }
        return invoice;
    }

    public void addService(Service service)
    {
        usageEventRepository.addService(service);
    }
}
