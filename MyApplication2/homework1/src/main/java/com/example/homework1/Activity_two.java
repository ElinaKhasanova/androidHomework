package com.example.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_two extends AppCompatActivity {

    EditText edit_name;
    EditText edit_phone;
    EditText edit_email;
    Button saveButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent save = new Intent();
                save.putExtra("name",edit_name.getText().toString());
                save.putExtra("phone",edit_phone.getText().toString());
                save.putExtra("email",edit_email.getText().toString());

                setResult(RESULT_OK,save);
                finish();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Activity_two.this,MainActivity.class);
                setResult(RESULT_CANCELED,cancel);
                finish();
            }
        });

    }
}
