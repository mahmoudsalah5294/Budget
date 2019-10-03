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

import com.activeandroid.query.Select;

import java.util.GregorianCalendar;

public class Update extends AppCompatActivity {

    EditText nameUpdate,numberUpdate;
    Button updateButton;
    Spinner typeSpinner;
    GregorianCalendar calendar;
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nameUpdate = findViewById(R.id.nameUpdate);
        numberUpdate = findViewById(R.id.numberUpdate);
        updateButton = findViewById(R.id.updateButton);
        typeSpinner = findViewById(R.id.typeSpinner);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nameUpdate.setText(bundle.getString("name"));
        numberUpdate.setText(""+bundle.getFloat("number"));

        if (bundle.getString("type").equals("Income")) {
            typeSpinner.setSelection(1);

        } else if (bundle.getString("type").equals("Food")) {
            typeSpinner.setSelection(2);


        } else if (bundle.getString("type").equals("Others")) {
            typeSpinner.setSelection(3);
        }



        updateButton.setEnabled(false);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (typeSpinner.getSelectedItem().toString().equals("Please Select"))
                    Toast.makeText(Update.this, "Please Select", Toast.LENGTH_SHORT).show();
                else {
                    updateButton.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    public void update(View view) {
        if (nameUpdate.getText().toString().equals("") || numberUpdate.getText().toString().equals("")) {
            Toast.makeText(this, "Fill The blank", Toast.LENGTH_SHORT).show();
        } else {

            if (typeSpinner.getSelectedItem().toString().equals("Income")) {
                Income income = new Income();
                income.name = nameUpdate.getText().toString();
                income.money  = numberUpdate.getText().toString();
                income.date = (calendar.DATE+"/"+months[calendar.MONTH]+"/"+calendar.YEAR);
                income.time = calendar.HOUR+"/"+calendar.MINUTE+"/"+calendar.SECOND;
                income.save();

            } else if (typeSpinner.getSelectedItem().toString().equals("Food")) {
                Food food = new Food();
                food.fname = nameUpdate.getText().toString();
                food.frmoney = numberUpdate.getText().toString();
                food.date = (calendar.DATE+"/"+months[calendar.MONTH]+"/"+calendar.YEAR);
                food.time = calendar.HOUR+"/"+calendar.MINUTE+"/"+calendar.SECOND;
                food.save();


            } else if (typeSpinner.getSelectedItem().toString().equals("Others")) {
                Others others = new Others();
                others.name = nameUpdate.getText().toString();
                others.money = numberUpdate.getText().toString();
                others.date = (calendar.DATE+"/"+months[calendar.MONTH]+"/"+calendar.YEAR);
                others.time = calendar.HOUR+"/"+calendar.MINUTE+"/"+calendar.SECOND;
                others.save();
            }


            Intent intent = new Intent(Update.this,MainActivity.class);
            startActivity(intent);

        }
    }
    }

