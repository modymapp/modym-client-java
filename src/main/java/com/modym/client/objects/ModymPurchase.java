/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.objects;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class ModymPurchase extends UDFType {

    private long purchaseId;

    private String purchaseReferenceId;

    private long customerId;

    private String customerReferenceId;

    private long sourceId;

    private String sourceReferenceId;

    private String sourceName;

    private String note;

    private LocalDateTime purchaseDate;

    private BigDecimal earnedPoints;

    private BigDecimal subtotal;

    private BigDecimal discount;

    private BigDecimal tax;

    private BigDecimal shipping;

    private BigDecimal grandtotal;

    private BigDecimal cost;

    private String currency;

    private PurchaseStatus status;

    private List<ModymPurchaseLineItem> lineItems;

    @Getter
    @Setter
    public static class ModymPurchaseLineItem {

        private long productId;

        private String productName;

        private String sku;

        private BigDecimal quantity;

        private BigDecimal unitPrice;

        private BigDecimal unitCost;

        private BigDecimal lineTotal;

    }

    public static enum PurchaseStatus {
        COMPLETE,
        CANCELLED,
        PENDING,
        CLOSED;

        @Override
        public String toString() {
            return WordUtils.capitalizeFully(this.name()).replaceAll("_", " ");
        };
    }

}
