/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class ModymCustomer extends UDFType implements Serializable {

    private static final long serialVersionUID = 1L;

    private long customerId;
    private String referenceId;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneMobile;
    private String phoneOther;
    private ModymGender gender;
    private ModymMaritalStatus maritalStatus;
    private LocalDate dateOfBirth;
    private Integer age;
    private String address1;
    private String address2;
    private String postcode;
    private String cityName;
    private String countryIso2;
    private String company;
    private String position;
    private String language;
    private LocalDate registered;

    private BigDecimal lifetimeRevenue = null;
    private String lifetimeRevenueCurrency = null;
    private Integer activityCount = null;
    private Integer purchaseCount = null;
    private LocalDate lastActivity = null;
    private LocalDate lastPurchase = null;

    private Long accountId;
    private Long levelId;
    private String levelName;
    private BigDecimal totalPoints;
    private BigDecimal availablePoints;
    private BigDecimal totalLifetimePoints;
    private BigDecimal totalLifetimeConsumedPoints;
    private BigDecimal totalPendingCreditPoints;
    private BigDecimal totalAuthorizedDebitPoints;
    
    private LocalDate loyaltyJoinDate;

    private boolean enabled;

    public enum ModymGender {
        MALE,
        FEMALE,
        UNSPECIFIED;
    }

    public enum ModymMaritalStatus {
        SINGLE,
        MARRIED,
        WIDOWED,
        DIVORCED,
        UNSPECIFIED;
    }
}