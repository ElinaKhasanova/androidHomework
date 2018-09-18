package com.example.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_REQUEST_CODE = 0;
    private static final int REQUEST_ID = 1;

    TextView name;
    TextView phone;
    TextView email;
    Button editButton;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        editButton = findViewById(R.id.btn_edit);
        sendButton = findViewById(R.id.btn_send);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = name.getText().toString() + phone.getText().toString() + email.getText().toString();
                Intent messageIntent = new Intent(Intent.ACTION_SEND);
                messageIntent.putExtra(Intent.EXTRA_TEXT, message);
                messageIntent.setType("text/plain");
                startActivityForResult(messageIntent, REQUEST_ID);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                name.setText(data.getStringExtra("name"));
                email.setText(data.getStringExtra("email"));
                phone.setText(data.getStringExtra("phone"));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_ID) {
            if (resultCode == RESULT_OK) {
                name.setText(data.getStringExtra("name"));
                email.setText(data.getStringExtra("email"));
                phone.setText(data.getStringExtra("phone"));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

    }

}

