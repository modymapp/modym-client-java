This is the official modym library for Java. 

How To Use
----------
The library is designed to provide data into and from https://api.modym.com, this library is intended to be used by modym clients.
To be able to use this library you need an api key and api secret that can be genrated from the modym admin UI



    Modym modym = new Modym("CLIENT_DOMAIN", "CLIENT_KEY", "CLIENT_KEY");
    ModymCustomer customer = modym.customerOperations().getCustomerByReferenceId("reference_id");
    ModymPointTransaction pointTransaction = modym.rewardOperations().createCreditTransaction(
        customer.getCustomerId(), new BigDecimal(100), 12, "granted 100 points as a reward");

    System.out.println(String.format("100 points granted to '%s %s' with the value: %.3f %s",
        customer.getFirstName(), customer.getLastName(), pointTransaction.getPointValue(),
        pointTransaction.getPointValueCurrency()));
