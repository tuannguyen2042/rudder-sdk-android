package com.rudderlabs.android.sdk.ecomm.events;

import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderProperty;
import com.rudderlabs.android.sdk.ecomm.ECommerceEvents;
import com.rudderlabs.android.sdk.ecomm.ECommerceOrder;
import com.rudderlabs.android.sdk.ecomm.ECommercePropertyBuilder;

public class OrderCancelledEvent extends ECommercePropertyBuilder {
    private ECommerceOrder order;

    public OrderCancelledEvent withOrder(ECommerceOrder order) {
        this.order = order;
        return this;
    }

    public OrderCancelledEvent withOrderBuilder(ECommerceOrder.Builder builder) {
        this.order = builder.build();
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.ORDER_CANCELLED;
    }

    @Override
    public RudderProperty build() throws RudderException {
        RudderProperty property = new RudderProperty();
        if (this.order == null) throw new RudderException("Order can not be null");
        property.setProperty(this.order);
        return property;
    }
}