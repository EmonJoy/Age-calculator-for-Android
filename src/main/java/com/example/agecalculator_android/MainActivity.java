package com.example.agecalculator_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtYear, txtMonth, txtDate;
    Button calculate_btn, clear_btn;
    TextView mainTxtView, calcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtYear = findViewById(R.id.txtYear);
        txtMonth = findViewById(R.id.txtMonth);
        txtDate = findViewById(R.id.txtDate);
        calculate_btn = findViewById(R.id.calculate_btn);
        clear_btn = findViewById(R.id.clear_btn);
        mainTxtView = findViewById(R.id.mainTxtView);
        calcc = findViewById(R.id.calcc);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAge();
            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }

        });
        show_calendar();
    }

    private void calculateAge() {
        try {
            int birthYear = Integer.parseInt(txtYear.getText().toString());
            int birthMonth = Integer.parseInt(txtMonth.getText().toString());
            int birthDay = Integer.parseInt(txtDate.getText().toString());

            Calendar today = Calendar.getInstance();
            int currentYear = today.get(Calendar.YEAR);
            int currentMonth = today.get(Calendar.MONTH) + 1;
            int currentDay = today.get(Calendar.DAY_OF_MONTH);

            int ageYears = currentYear - birthYear;
            int ageMonths = currentMonth - birthMonth;
            int ageDays = currentDay - birthDay;

            if (ageDays < 0) {
                ageMonths--;
                today.add(Calendar.MONTH, -1);
                ageDays += today.getActualMaximum(Calendar.DAY_OF_MONTH);
            }

            if (ageMonths < 0) {
                ageYears--;
                ageMonths += 12;
            }
            mainTxtView.setText("Age: " + ageYears + " years, " + ageMonths + " months, and " + ageDays + " days.");
        } catch (NumberFormatException e) {
            mainTxtView.setText("Please enter valid numbers.");
        }
    }

    private void clearFields() {
        txtYear.setText("");
        txtMonth.setText("");
        txtDate.setText("");
        mainTxtView.setText("");
    }

    private void show_calendar(){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) +1;
        int year = c.get(Calendar.YEAR);

        calcc.setText("Today: "+ month + "/"+ day + "/"+ year);

    }

}
