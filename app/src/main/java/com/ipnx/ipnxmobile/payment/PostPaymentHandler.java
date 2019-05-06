package com.ipnx.ipnxmobile.payment;

import com.interswitchng.sdk.payment.model.PurchaseResponse;

public interface PostPaymentHandler {
    void postPayment(PurchaseResponse response);
}
