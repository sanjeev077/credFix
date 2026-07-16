Usage-Based Billing System

A billing engine that tracks resource usage per user, applies service-specific pricing
models, and generates invoices for a billing period.

Design
Usage ingestion — BillingManager.addUsageEvent, stored via LocalStorage
Pricing strategy — PricingModel interface with FlatBill, TierBill,
SubscriptionBill implementations. Adding a new pricing model = one new class, no
changes to existing code.
Invoice assembly — BillingManager.generateInvoice groups usage by service/resource
and builds per-resource, per-service, and total costs.
Storage — behind the UsageEventRepository interface, swappable from in-memory.
Config — rates and tiers live in pricing.properties, loaded at startup. Rate
changes need no code changes.
Money — BigDecimal throughout, rounded once via MoneyUtil.
Billing period — half-open [start, end), so adjacent periods never overlap.

Sample output
User : Sanjeev
Invoice Id fcf73137-2233-4b94-a51f-7193200dc438
Service : DUBBING
Resource : API_RESOURCE cost 16.380
Service cost 16.380
Total cost 16.380

User : Ashok
Invoice Id e202dfe9-00b8-4e2c-906a-584da97dfa6d
Service : TRANSLATION
Resource : STORAGE_RESOURCE cost 250.000
Service cost 250.000
Service : TRANSCRIPTION
Resource : TOKEN_RESOURCE cost 408.000
Resource : API_RESOURCE cost 1021.080
Resource : STORAGE_RESOURCE cost 389.440
Service cost 1818.520
Total cost 2068.520
