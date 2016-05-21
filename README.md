# modym java client
This is the official modym library for Java. 

[http://www.modym.com](http://www.modym.com)

### Latest Version 
##### [![](https://jitpack.io/v/modymapp/modym-client-java.svg)](https://jitpack.io/#modymapp/modym-client-java) 
_May 21st, 2016_ - [v0.1.0-alpha](https://github.com/modymapp/modym-client-java/releases/tag/v0.1.0-alpha)

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
    <version>v0.1.0-alpha</version>
</dependency>
```

### How To Use
The library is designed to provide data into and from https://api.modym.com, this library is intended to be used by modym clients.
To be able to use this library you need an api key and api secret that can be genrated from the modym admin UI

```java
Modym modym = new Modym("CLIENT_DOMAIN", "CLIENT_KEY", "CLIENT_KEY");
ModymCustomer customer = modym.customerOperations().getCustomerByReferenceId("reference_id");
ModymPointTransaction pointTransaction = modym.rewardOperations().createCreditTransaction(
customer.getCustomerId(), new BigDecimal(100), 12, "granted 100 points as a reward");

System.out.println(String.format("100 points granted to '%s %s' with the value: %.3f %s",
customer.getFirstName(), customer.getLastName(), pointTransaction.getPointValue(), pointTransaction.getPointValueCurrency()));
```

