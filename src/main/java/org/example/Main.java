package org.example;

import org.example.configurations.PricingConfigLoader;
import org.example.entities.*;
import org.example.entities.pricing.FlatBill;
import org.example.entities.pricing.SubscriptionBill;
import org.example.entities.pricing.TierBill;
import org.example.enums.ResourceType;
import org.example.enums.ServiceType;
import org.example.repositories.LocalStorage;
import org.example.services.BillingManager;

import java.math.BigDecimal;


public class Main {

    /*
    Assumptions
    user provided time is in long systemTime milli Seconds
    assumption pricing could be different for a same resource under different services
    what if the price change later usage have been done and compute billing with new price or old price ,assumption calculate bill with new price when price changes
     */


    /*
    could have been done / scope of improvement
    tier billing can be done using chain of responsibility
     */



    static void main() {

        PricingConfigLoader config = new PricingConfigLoader("pricing.properties");

        //create storage repository
        LocalStorage localStorage = new LocalStorage();

        //create Billing manager
        BillingManager billingManager =  BillingManager.getBillingManager(localStorage);

        //create Resources
        Resource dubbingAPIResource = new Resource(new FlatBill(config.getDecimal("dubbing.flat.ratePerUnit")) , ResourceType.API_RESOURCE);


        Resource translationStorageResource = new Resource(new SubscriptionBill(config.getDecimal("translation.subscription.fixPrice"),
                config.getDecimal("translation.subscription.fixAllowedQuantity"), config.getDecimal("translation.subscription.perUnitRate")), ResourceType.STORAGE_RESOURCE);

        Resource transcriptiontokenResource = new Resource( new TierBill(config.getDecimalList("transcription.tier.prices"),
                config.getDecimalList("transcription.tier.amounts")), ResourceType.TOKEN_RESOURCE);
        Resource transcriptionApiResource = new Resource(new FlatBill(config.getDecimal("transcription.flat.ratePerUnit")), ResourceType.API_RESOURCE);
        Resource transcriptionStorageResource = new Resource(new SubscriptionBill(config.getDecimal("transcription.subscription.fixPrice"),
                config.getDecimal("transcription.subscription.fixAllowedQuantity"), config.getDecimal("transcription.subscription.perUnitRate")), ResourceType.STORAGE_RESOURCE);


        //create Service
        Service dubbingService = new Service(ServiceType.DUBBING);
        dubbingService.addResource(dubbingAPIResource);

        Service translationService = new Service(ServiceType.TRANSLATION);
        translationService.addResource(translationStorageResource);

        Service transcriptionService = new Service(ServiceType.TRANSCRIPTION);
        transcriptionService.addResource(transcriptiontokenResource);
        transcriptionService.addResource(transcriptionApiResource);
        transcriptionService.addResource(transcriptionStorageResource);

        billingManager.addService(dubbingService);
        billingManager.addService(transcriptionService);
        billingManager.addService(translationService);



        //create users
        User userSanjeev = new User("Sanjeev");
        userSanjeev.subscribeToService(ServiceType.DUBBING);
        billingManager.addUser(userSanjeev);

        User userAshok = new User("Ashok");
        userAshok.subscribeToService(ServiceType.TRANSLATION);
        userAshok.subscribeToService(ServiceType.TRANSCRIPTION);
        billingManager.addUser(userAshok);

        //add usage event
        billingManager.addUsageEvent(new UsageEvent(userSanjeev.getUid(), dubbingAPIResource.getResourceId(), dubbingService.getServiceType(), new BigDecimal(2),System.currentTimeMillis(),"seconds"));
        billingManager.addUsageEvent(new UsageEvent(userSanjeev.getUid(), dubbingAPIResource.getResourceId(), dubbingService.getServiceType(), new BigDecimal(5),System.currentTimeMillis(),"seconds"));

        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), translationStorageResource.getResourceId(), translationService.getServiceType(), new BigDecimal(20),System.currentTimeMillis(),"gb/hour"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), translationStorageResource.getResourceId(), translationService.getServiceType(), new BigDecimal(45),System.currentTimeMillis(),"gb/hour"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptiontokenResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(12),System.currentTimeMillis(),"token"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptiontokenResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(40),System.currentTimeMillis(),"token"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptiontokenResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(100),System.currentTimeMillis(),"token"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptionApiResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(100),System.currentTimeMillis(),"token"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptionApiResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(154),System.currentTimeMillis(),"token"));
        billingManager.addUsageEvent(new UsageEvent(userAshok.getUid(), transcriptionStorageResource.getResourceId(), transcriptionService.getServiceType(), new BigDecimal(100),System.currentTimeMillis(),"token"));


        Invoice sanjeevInvoice = billingManager.generateInvoice(userSanjeev.getUid(),System.currentTimeMillis()-2000, System.currentTimeMillis()+1000);
        Invoice ashokInvoice = billingManager.generateInvoice(userAshok.getUid(),System.currentTimeMillis()-2000, System.currentTimeMillis()+1000);

        sanjeevInvoice.showInvoice();
        System.out.println();
        ashokInvoice.showInvoice();

    }
}
