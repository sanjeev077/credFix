package org.example.entities.pricing;

import org.example.entities.UsageEvent;
import org.example.enums.BillingType;

import java.math.BigDecimal;
import java.util.List;

public interface PricingModel {
    BillingType getBillingType();
    public BigDecimal computeBill(List<UsageEvent> usageEventList);
}
