package com.mahmoudsalah.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Add extends AppCompatActivity {
    EditText nameText, numText;
    Spinner typeSpinner;
    Button addButton;
    GregorianCalendar calendar;
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameText = findViewById(R.id.nameText);
        numText = findViewById(R.id.numText);
        typeSpinner = findViewById(R.id.typeSpinner);
        addButton = findViewById(R.id.addButton);





        addButton.setEnabled(false);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (typeSpinner.getSelectedItem().toString().equals("Please Select")){

                }
                else {
                    addButton.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        calendar = new GregorianCalendar();


    }

    public void add(View view) {
        if (nameText.getText().toString().equals("") || numText.getText().toString().equals("")) {
            Toast.makeText(this, "Fill The blank", Toast.LENGTH_SHORT).show();
        } else {

                    if (typeSpinner.getSelectedItem().toString().equals("Income")) {
                        Income income = new Income();
                        income.name = nameText.getText().toString();
                        income.money = (numText.getText().toString());
                        income.date = (calendar.get(Calendar.DATE)+"/"+months[calendar.get(Calendar.MONTH)]+"/"+calendar.get(Calendar.YEAR));
                        income.time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
                        income.save();

                    } else if (typeSpinner.getSelectedItem().toString().equals("Food")) {
                        Food food = new Food();
                        food.fname = nameText.getText().toString();
                        food.frmoney = numText.getText().toString();
                        food.date = (calendar.get(Calendar.DATE)+"/"+months[calendar.get(Calendar.MONTH)]+"/"+calendar.get(Calendar.YEAR));
                        food.time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
                        food.save();


                    } else if (typeSpinner.getSelectedItem().toString().equals("Others")) {
                        Others others = new Others();
                        others.name = nameText.getText().toString();
                        others.money = numText.getText().toString();
                        others.date = (calendar.get(Calendar.DATE)+"/"+months[calendar.get(Calendar.MONTH)]+"/"+calendar.get(Calendar.YEAR));
                        others.time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
                        others.save();
                    }


            Intent intent = new Intent(Add.this,MainActivity.class);
            startActivity(intent);

        }
    }
}