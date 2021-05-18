package com.aiyolabs.hashchat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aiyolabs.hashchat.adapters.MessageSectionAdapter;
import com.aiyolabs.hashchat.model.CategoryPojo;
import com.aiyolabs.hashchat.model.MessagePojo;
import com.aiyolabs.hashchat.model.SMS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    List<SMS> smsList=new ArrayList<>();
    public static boolean initialSleep=true;
    List<CategoryPojo> messageArrayList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            android.Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        try{
            if(initialSleep)
            {
               initialSleep=false;
                Thread.sleep(4000);
            }

            getsms();

        }catch (Exception e)
        {

        }

    }
    public void getsms()
    {
        try{
            Uri uriSMSURI = Uri.parse("content://sms/inbox");
            Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);

            while (cur != null && cur.moveToNext()) {
                String address = cur.getString(cur.getColumnIndex("address"));
                String body = cur.getString(cur.getColumnIndexOrThrow("body"));
                String date=cur.getString(cur.getColumnIndexOrThrow("date"));
                String msgTime="";
                // sms.add("Number: " + address + " .Message: " + body);
                Long timestamp = Long.parseLong(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                long now =System.currentTimeMillis();
                long diff=now-timestamp;

                if (diff < MINUTE_MILLIS) {
                    msgTime="0 hour ago";
                }
                if (diff < 24 * HOUR_MILLIS)
                {
                    long validDate=diff/HOUR_MILLIS;

                    if(validDate==1)
                    {
                        msgTime="1 hour ago";
                    }else if(validDate == 2){
                        msgTime="2 hours ago";
                    }else if(validDate == 3)
                    {
                        msgTime="3 hours ago";
                    }else if(validDate == 6)
                    {
                        msgTime="6 hours ago";
                    }else if(validDate == 12)
                    {
                        msgTime="12 hours ago";
                    }
                }
                else if (diff < 48 * HOUR_MILLIS)
                {
                    msgTime="1 day ago";
                }
                Date finaldate = calendar.getTime();
                String smsDate = finaldate.toString();
                SMS sms = new SMS();
                sms.address=address;sms.message=body;sms.date=msgTime;
                if(!msgTime.equalsIgnoreCase(""))
                    smsList.add(sms);
            }

            if (cur != null) {
                cur.close();
            }
            setAdapter();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please Enable ReadSMS Permission",Toast.LENGTH_LONG).show();
        }

    }
    public void setAdapter()
    {
        List<MessagePojo> oneHourList=new ArrayList<>();
        List<MessagePojo> zeroHourList=new ArrayList<>();
        List<MessagePojo> twoHoursList=new ArrayList<>();
        List<MessagePojo> threeHoursList=new ArrayList<>();
        List<MessagePojo> sixHoursList=new ArrayList<>();
        List<MessagePojo> twelveHoursList=new ArrayList<>();
        List<MessagePojo> oneDayList=new ArrayList<>();

        for (SMS smsObj:smsList
             ) {
            if(smsObj.getDate().equalsIgnoreCase("1 hour ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                oneHourList.add(msgPojo);
            }
            if(smsObj.getDate().equalsIgnoreCase("0 hour ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("true");
                zeroHourList.add(msgPojo);
            }
            if(smsObj.getDate().equalsIgnoreCase("2 hours ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                twoHoursList.add(msgPojo);
            }else if(smsObj.getDate().equalsIgnoreCase("3 hours ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                threeHoursList.add(msgPojo);
            }else if(smsObj.getDate().equalsIgnoreCase("6 hours ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                sixHoursList.add(msgPojo);
            }else if(smsObj.getDate().equalsIgnoreCase("12 hours ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                twelveHoursList.add(msgPojo);
            }else if(smsObj.getDate().equalsIgnoreCase("1 day ago"))
            {
                MessagePojo msgPojo=new MessagePojo();
                msgPojo.setNumber(smsObj.getAddress());
                msgPojo.setMessage(smsObj.getMessage());
                msgPojo.setAnimation("false");
                oneDayList.add(msgPojo);
            }
        }
        CategoryPojo categoryPojo1=new CategoryPojo();
        CategoryPojo categoryPojo0=new CategoryPojo();
        CategoryPojo categoryPojo2=new CategoryPojo();
        CategoryPojo categoryPojo3=new CategoryPojo();
        CategoryPojo categoryPojo12=new CategoryPojo();
        CategoryPojo categoryPojo1day=new CategoryPojo();
        CategoryPojo categoryPojo6=new CategoryPojo();


        categoryPojo0.setCategoryTitle("0 hour ago");
        categoryPojo0.setMessagePojoList(zeroHourList);

        categoryPojo1.setCategoryTitle("1 hour ago");
        categoryPojo1.setMessagePojoList(oneHourList);

        categoryPojo2.setCategoryTitle("2 hours ago");
        categoryPojo2.setMessagePojoList(twoHoursList);

        categoryPojo3.setCategoryTitle("3 hours ago");
        categoryPojo3.setMessagePojoList(threeHoursList);

        categoryPojo6.setCategoryTitle("6 hours ago");
        categoryPojo6.setMessagePojoList(sixHoursList);

        categoryPojo12.setCategoryTitle("12 hours ago");
        categoryPojo12.setMessagePojoList(twelveHoursList);

        categoryPojo1day.setCategoryTitle("1 day ago");
        categoryPojo1day.setMessagePojoList(oneDayList);

        if(!zeroHourList.isEmpty())
           messageArrayList.add(categoryPojo0);
        messageArrayList.add(categoryPojo1);
        messageArrayList.add(categoryPojo2);
        messageArrayList.add(categoryPojo3);
        messageArrayList.add(categoryPojo6);
        messageArrayList.add(categoryPojo12);
        messageArrayList.add(categoryPojo1day);


        recyclerView=findViewById(R.id.main_recycler);
         linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MessageSectionAdapter(getApplicationContext(),messageArrayList));




    }
    public  boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
