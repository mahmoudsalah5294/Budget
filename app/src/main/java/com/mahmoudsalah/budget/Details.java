package com.mahmoudsalah.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView nameText,numberText,clockText,dateText,typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameText = findViewById(R.id.nameText);
        numberText = findViewById(R.id.numberText);
        clockText = findViewById(R.id.clockText);
        dateText = findViewById(R.id.dateText);
        typeText = findViewById(R.id.typeText);


        typeText.setText(getIntent().getStringExtra("type"));
        nameText.setText(getIntent().getStringExtra("name"));
        numberText.setText(getIntent().getStringExtra("number"));
        dateText.setText(getIntent().getStringExtra("date"));
        clockText.setText(getIntent().getStringExtra("time"));


    }
}
