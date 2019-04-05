package com.ipnx.ipnxmobile.payment;

import android.content.Context;
import android.widget.Toast;

import com.interswitchng.sdk.payment.IswCallback;
import com.interswitchng.sdk.payment.model.PurchaseResponse;
import com.ipnx.ipnxmobile.RenewPaymentActivity;

public class PaymentCallback extends IswCallback<PurchaseResponse> {

        private transient Context context;
        private transient String ref;

        public PaymentCallback(Context context, String ref) {
            this.context = context;
            this.ref = ref;
        }

    @Override
    public void onError(Exception error) {
        // Handle error.
        // Payment not successful.
        Toast.makeText(context, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        System.out.println(error.getMessage());
    }

    @Override
    public void onSuccess(PurchaseResponse response) {
        /* Handle success.
           Payment successful. The response object contains fields transactionIdentifier,
           message, amount, token, tokenExpiryDate, panLast4Digits, transactionRef and cardType.
           Save the token, tokenExpiryDate, cardType and panLast4Digits
           in order to pay with the token in the future.
        */
        Toast.makeText(context, "Successful: "+ response + " show", Toast.LENGTH_SHORT).show();
        System.out.println(response);
        System.out.println(ref);
    }
}
