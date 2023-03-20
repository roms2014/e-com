package com.example.e_com;

import static android.app.job.JobInfo.PRIORITY_HIGH;
import static android.app.job.JobInfo.PRIORITY_LOW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_com.model.Order;
import com.example.e_com.model.TV;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class order_page extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private List<String> tvTitle = new ArrayList<>();
    private DBwork db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), order_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(false)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("e-com")
                        .setContentText("Покупки ждут!")
                        .setPriority(PRIORITY_HIGH);

        ListView orders_list = findViewById(R.id.orders_list);

        for(TV tv : MainActivity.fullTVList){
            if(Order.items_id.contains(tv.getId()))
                tvTitle.add(tv.getTitle());
        }
        db = new DBwork(this);
        db.openConnection();
        int random = (int)(Math.random() * ((10000000 - 0) + 1)) + 0;
        for (String s : tvTitle){
            db.dbInsert_tv_order("'" + Integer.toString(random) + "' ,'" + s + "'");
        }
        //db.closeConnection();
        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tvTitle));

        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }

    public static void createChannelIfNeeded(NotificationManager manager){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    public void saveText(View view){
        FileOutputStream fos = null;
        try{
            String text = "";
            for (String s :tvTitle){
                text += s + "\n";
            }
            fos = openFileOutput("C:\\Users\\79281\\output.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch (IOException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fos!=null)
                    fos.close();
            }
            catch (IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}