package org.example.entities;

import org.example.entities.dto.InvoiceResourceDTO;
import org.example.entities.dto.InvoiceServiceDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Invoice {
    public String userName;
    public String invoiceId;
    List<InvoiceServiceDTO>invoiceServiceDTOList;
    BigDecimal totalCost;
    long startTIme;
    long endTime;

    public Invoice(long startTIme, long endTime) {
        invoiceId = UUID.randomUUID().toString();
        invoiceServiceDTOList = new ArrayList<>();
        totalCost = BigDecimal.ZERO;
        this.startTIme = startTIme;
        this.endTime = endTime;
    }

    public void addService(InvoiceServiceDTO service)
    {
        invoiceServiceDTOList.add(service);
        totalCost = totalCost.add(service.getServiceCost());
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public List<InvoiceServiceDTO> getInvoiceServiceDTOList() {
        return invoiceServiceDTOList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTIme() {
        return startTIme;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void showInvoice()
    {
        System.out.println("User : "+userName);
        System.out.println("Invoice Id "+invoiceId );
        for(InvoiceServiceDTO invoiceServiceDTO:invoiceServiceDTOList)
        {
            System.out.println("Service : "+invoiceServiceDTO.serviceType);
            for(InvoiceResourceDTO invoiceResourceDTO:invoiceServiceDTO.resources)
            {
                System.out.println("Resource : "+invoiceResourceDTO.getResourceType()+" cost "+invoiceResourceDTO.getResourceCost());
            }
            System.out.println("Service cost "+invoiceServiceDTO.serviceCost);
        }
        System.out.println("Total cost "+totalCost);
    }
}
