# modym java client
This is the official modym library for Java. 

[http://www.modym.com](http://www.modym.com)

### Latest Version 
##### [![](https://jitpack.io/v/modymapp/modym-client-java.svg)](https://jitpack.io/#modymapp/modym-client-java) 
_Janary 11th, 2017_ - [0.1.8-alpha](https://github.com/modymapp/modym-client-java/releases/tag/0.1.8-alpha)

### Maven
This project uses JitPack as a maven repository.

Respository:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Dependency:
```xml
<dependency>
    <groupId>com.github.modymapp</groupId>
    <artifactId>modym-client-java</artifactId>
    <version>0.1.8-alpha</version>
</dependency>
```


### Gradle
JitPack gradle repository.

Respository:
```code
    allprojects {
	    repositories {
		    ...
		    maven { url "https://jitpack.io" }
	    }
    }
```

Dependency:
```code
    dependencies {
        compile 'com.github.modymapp:modym-client-java:0.1.8-alpha'
    }
```

### How To Use
The library is designed to provide data into and from https://api.modym.com, this library is intended to be used by modym clients.
To be able to use this library you need an api key and api secret that can be genrated from the modym admin UI

```java
//connect to modym api, replace {CLIENT_DOMAIN}, {CLIENT_KEY} and {CLIENT_KEY} with the ones from modym admin api settings page.
Modym modym = new Modym("{CLIENT_DOMAIN}", "{CLIENT_KEY}", "{CLIENT_KEY}");

 // {reference_id} is the id of the customer in the client environment
String customerReferenceId = "{reference_id}";
ModymCustomer customer;

// retrieve customer details
customer = modym.customerOperations().getCustomerByReferenceId(customerReferenceId);

// Print customer name along with available and total points
System.out.println(String.format("customer: '%s %s'\navailable points: %f\ntotal points: %f",
        customer.getFirstName(), customer.getLastName(), customer.getAvailablePoints(),
        customer.getTotalPoints()));

// grant 100 points to the customer valid for 12 months
ModymPointTransaction credit = modym.rewardOperations().createCreditTransaction(customer.getCustomerId(),
        new BigDecimal(100), 12, "Reward on visit");

// after adding the points print customer name along with available and total points
customer = modym.customerOperations().getCustomerByReferenceId(customerReferenceId);
System.out.println();
System.out.println(String.format(
        "Before Approve:\ncustomer: '%s %s'\navailable points: %f\ntotal points: %f",
        customer.getFirstName(), customer.getLastName(), customer.getAvailablePoints(),
        customer.getTotalPoints()));

// approve the points granted to the customer
modym.rewardOperations().approveCreditTransaction(Long.parseLong(credit.getTransactionId()));

// after approving the points print customer name along with available and total points
customer = modym.customerOperations().getCustomerByReferenceId(customerReferenceId);
System.out.println();
System.out.println(String.format(
        "After Approve:\ncustomer: '%s %s'\navailable points: %f\ntotal points: %f",
        customer.getFirstName(), customer.getLastName(), customer.getAvailablePoints(),
        customer.getTotalPoints()));
```

### Extras
Modym API documentation is a great resource : http://www.modym.com/api/
