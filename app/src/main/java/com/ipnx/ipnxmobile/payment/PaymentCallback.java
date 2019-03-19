package com.ipnx.ipnxmobile.payment;

import android.content.Context;
import android.widget.Toast;

import com.interswitchng.sdk.payment.IswCallback;
import com.interswitchng.sdk.payment.model.PurchaseResponse;
import com.ipnx.ipnxmobile.RenewPaymentActivity;

public class PaymentCallback extends IswCallback<PurchaseResponse> {

        private transient Context context;

        public PaymentCallback(Context context) {
            this.context = context;
        }

    @Override
    public void onError(Exception error) {
        // Handle error.
        // Payment not successful.
        Toast.makeText(context, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(PurchaseResponse response) {
        /* Handle success.
           Payment successful. The response object contains fields transactionIdentifier,
           message, amount, token, tokenExpiryDate, panLast4Digits, transactionRef and cardType.
           Save the token, tokenExpiryDate, cardType and panLast4Digits
           in order to pay with the token in the future.
        */
        Toast.makeText(context, "Successful: "+ response.getResponseCode(), Toast.LENGTH_SHORT).show();
    }
}
