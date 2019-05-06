package com.ipnx.ipnxmobile.payment;

import android.content.Context;
import android.widget.Toast;

import com.interswitchng.sdk.payment.IswCallback;
import com.interswitchng.sdk.payment.model.PurchaseResponse;
import com.ipnx.ipnxmobile.RenewPaymentActivity;

public class PaymentCallback extends IswCallback<PurchaseResponse> {

        private transient Context context;
        private transient String ref;
        private transient PostPaymentHandler handler;

        public PaymentCallback(Context context, PostPaymentHandler handler, String ref) {
            this.context = context;
            this.handler = handler;
            this.ref = ref;
        }

    @Override
    public void onError(Exception error) {
        // Handle error.
        // TransactionPayment not successful.
        Toast.makeText(context, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        System.out.println(error.getMessage());
    }

    @Override
    public void onSuccess(PurchaseResponse response) {
        /* Handle success.
           TransactionPayment successful. The response object contains fields transactionIdentifier,
           message, amount, token, tokenExpiryDate, panLast4Digits, transactionRef and cardType.
           Save the token, tokenExpiryDate, cardType and panLast4Digits
           in order to pay with the token in the future.
        */
        Toast.makeText(context, "Successful: "+ response + " show", Toast.LENGTH_SHORT).show();
        System.out.println(response.getACSUrl());
        System.out.println(response.getAmount());
        System.out.println(response.getCardType());
        System.out.println(response.getEciFlag());
        System.out.println(response.getMD());
        System.out.println(response.getMessage());
        System.out.println(response.getOtpTransactionIdentifier());
        System.out.println(response.getPanLast4Digits());
        System.out.println(response.getPaReq());
        System.out.println(response.getPaymentId());
        System.out.println(response.getResponseCode());
        System.out.println(response.getTermUrl());
        System.out.println(response.getToken());
        System.out.println(response.getTokenExpiryDate());
        System.out.println(response.getTransactionId());
        System.out.println(response.getTransactionIdentifier());
        System.out.println(response.getTransactionRef());
        System.out.println(ref);

        handler.postPayment(response);
    }
}
