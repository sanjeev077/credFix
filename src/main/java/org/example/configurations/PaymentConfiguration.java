package org.example.configurations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentConfiguration {
    public static final BigDecimal DubbingFlatCharge = new BigDecimal("2.34");
    public static final BigDecimal TranslationFlatCharge = new BigDecimal(3);
    public static final BigDecimal TranscriptionFlatCharge = new BigDecimal("4.02");

    public static final BigDecimal TranslationSubscriptionFixCharge= new BigDecimal(2);
    public static final BigDecimal TranslationSubscriptionFixAmount=new BigDecimal(3);
    public static final BigDecimal TranslationSubscriptionUnitCot=new BigDecimal(4);

    public static final BigDecimal TranscriptionSubscriptionFixCharge=new BigDecimal("2.16");
    public static final BigDecimal TranscriptionSubscriptionFixAmount=new BigDecimal("3.18");
    public static final BigDecimal TranscriptionSubscriptionUnitCot=new BigDecimal(4);

    public static List<BigDecimal> TranscriptionTierAmountUnit = List.of(new BigDecimal(10), new BigDecimal(20), new BigDecimal(10000));
    public static List<BigDecimal> TranscriptionTierPrice = List.of(new BigDecimal(10) ,new BigDecimal(5), new BigDecimal(2));
}
