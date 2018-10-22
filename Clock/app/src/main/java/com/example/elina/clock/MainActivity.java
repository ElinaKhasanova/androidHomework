package com.example.elina.clock;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvTime;
    TextView tvState;
    Button btnStart;
    Button btnStop;
    AlarmManager alarmManager;
    Calendar time = Calendar.getInstance();
    PendingIntent pIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tv_time);
        tvState = findViewById(R.id.tv_state);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                setTimeText("Включен");

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

                pIntent = PendingIntent.getBroadcast(MainActivity.this,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                if (alarmManager != null) {
                    alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(System.currentTimeMillis() + 10000, pIntent),
                            pIntent);
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeText("Выключен");
                alarmManager.cancel(pIntent);
            }
        });

        createChannel();
    }

    private void setTimeText(String s){
        tvState.setText(s);
    }

    public void setTime(View view) {
        new TimePickerDialog(MainActivity.this, timeSetListener,
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialTime() {

        tvTime.setText(DateUtils.formatDateTime(this,
                time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            setInitialTime();
        }
    };

    public void createChannel() {
        String cnlDesc = "This channel is used by alarm application";
        String cnlID = "first_alarm_channel";

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(cnlID, "Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(cnlDesc);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
