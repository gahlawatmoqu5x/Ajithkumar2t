package com.aiyolabs.hashchat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import static android.app.Notification.DEFAULT_SOUND;

public class SmsListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){

                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        long time=System.currentTimeMillis();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            String CHANNEL_ID="com.hasgchat";
                       CharSequence name = "message";
                        String description = "Description";
                      int importance = NotificationManager.IMPORTANCE_DEFAULT;
                       NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                          channel.setDescription(description);
                            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                             notificationManager.createNotificationChannel(channel);

                            int icon = R.drawable.icon;
                            Intent intent2 = new Intent(context, MainActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setSmallIcon(R.drawable.icon)
                                    .setContentTitle(msg_from)
                                    .setContentText(msgBody)
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setContentIntent(pendingIntent)
                                    .setDefaults(DEFAULT_SOUND)
                                    .setAutoCancel(true);
                            notificationManager.notify(1, builder.build());
                        }

                    }
                }catch(Exception e){
                    Log.i("*******",e.toString());
                }
            }
        }
    }
}
