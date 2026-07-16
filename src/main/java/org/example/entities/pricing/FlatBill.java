package org.example.entities.pricing;

import org.example.enums.BillingType;
import org.example.util.MoneyUtil;
import org.example.entities.UsageEvent;

import java.math.BigDecimal;
import java.util.List;

public class FlatBill implements PricingModel{

    private final BigDecimal flatRatePerUnit;

    public FlatBill(BigDecimal flatRatePerUnit) {
        this.flatRatePerUnit = flatRatePerUnit;
    }

    @Override
    public BillingType getBillingType() {
        return BillingType.FLAT_BILL;
    }

    @Override
    public BigDecimal computeBill(List<UsageEvent>usageEventList) {
        BigDecimal totalUsage = BigDecimal.ZERO;
        for (UsageEvent event : usageEventList)
        {
            totalUsage = totalUsage.add(event.getQuantity());
        }
        return MoneyUtil.round(totalUsage.multiply(flatRatePerUnit));
    }


}
