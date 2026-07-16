package org.example.entities.pricing;

import org.example.entities.UsageEvent;
import org.example.enums.BillingType;
import org.example.util.MoneyUtil;

import java.math.BigDecimal;
import java.util.List;

public class SubscriptionBill implements PricingModel{
    private final BigDecimal fixPrice;
    private final BigDecimal fixAllowedQuantity;
    private final BigDecimal perUnitRate;

    public SubscriptionBill(BigDecimal fixPrice, BigDecimal fixAllowedQuantity, BigDecimal perUnitRate) {
        this.fixPrice = fixPrice;
        this.fixAllowedQuantity = fixAllowedQuantity;
        this.perUnitRate = perUnitRate;
    }

    @Override
    public BillingType getBillingType() {
        return BillingType.SUBSCRIPTION_BILL;
    }

    @Override
    public BigDecimal computeBill(List<UsageEvent> usageEventList) {
        BigDecimal totalUsage = BigDecimal.ZERO;
        for (UsageEvent event : usageEventList)
        {
           totalUsage = totalUsage.add(event.getQuantity());
        }
        if(totalUsage.compareTo(fixAllowedQuantity)<=0)
        {
            return MoneyUtil.round(fixPrice);
        }
        BigDecimal overage = totalUsage.subtract(fixAllowedQuantity);
        return MoneyUtil.round(fixPrice.add(perUnitRate.multiply(overage)));
    }
}
