package com.example.elina.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvTime;
    TextView stateText;
    Button startBtn;
    Button stopBtn;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Calendar time = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tvTime);
        stateText = findViewById(R.id.state);
        startBtn = findViewById(R.id.btn_start);
        stopBtn = findViewById(R.id.btn_stop);

        final Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeText("Включен");

                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);

//                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
//                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
//                        pendingIntent);
//                Toast.makeText(MainActivity.this, "bvnvmb", Toast.LENGTH_SHORT).show();

            }

        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeText("Выключен");
            }
        });
    }

//    private void startAlarm(){
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
//    }

    private void setTimeText(String s){
        stateText.setText(s);
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
}
