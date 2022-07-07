package com.example.lab10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            SmsMessage smsMessage = msgs[0];
            if (smsMessage != null) {
                String sender = smsMessage.getDisplayOriginatingAddress();
                String smsChunk = smsMessage.getDisplayMessageBody();
                String data = "From: " + sender + "\n\nText Received: \n" + smsChunk;
                Toast.makeText(context, data, Toast.LENGTH_LONG).show();
            }
        }
    }
}