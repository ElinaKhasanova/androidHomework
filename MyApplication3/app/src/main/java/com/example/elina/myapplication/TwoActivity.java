package com.example.elina.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {
    TextView name;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        name = findViewById(R.id.tv_name);
        name.setText(getIntent().getStringExtra("name"));
        description = findViewById(R.id.tv_description);
        description.setText(getIntent().getStringExtra("description"));
    }
}
