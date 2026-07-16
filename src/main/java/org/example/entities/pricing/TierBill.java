package org.example.entities.pricing;

import org.example.entities.UsageEvent;
import org.example.enums.BillingType;
import org.example.util.MoneyUtil;

import java.math.BigDecimal;
import java.util.List;

public class TierBill implements PricingModel{
    private final List<BigDecimal>tierPrice;
    private final List<BigDecimal>tierAmount;

    public TierBill(List<BigDecimal> tierPrice, List<BigDecimal> tierAmount) {
        this.tierPrice = tierPrice;
        this.tierAmount = tierAmount;
    }

    @Override
    public BillingType getBillingType() {
        return BillingType.TIER_BILL;
    }

    @Override
    public BigDecimal computeBill(List<UsageEvent> usageEventList) {
        BigDecimal totalUsage = BigDecimal.ZERO;
        for (UsageEvent event : usageEventList)
        {
            totalUsage = totalUsage.add(event.getQuantity());
        }
        int idx=0;
        BigDecimal cost = BigDecimal.ZERO;
        while(idx< tierAmount.size()-1 && totalUsage.compareTo(BigDecimal.ZERO)>0)
        {
            BigDecimal price = tierPrice.get(idx);
            BigDecimal allowedUnit = tierAmount.get(idx);
            if(totalUsage.compareTo(allowedUnit)>0)
            {
                cost = cost.add(price.multiply(allowedUnit));
                totalUsage = totalUsage.subtract(allowedUnit);
            }else{
                cost = cost.add(price.multiply(totalUsage));
                totalUsage = BigDecimal.ZERO;
            }
            idx++;
        }
        BigDecimal lastPrice = tierPrice.get(tierPrice.size() - 1);
        cost = cost.add(lastPrice.multiply(totalUsage));
        return MoneyUtil.round(cost);
    }
}
