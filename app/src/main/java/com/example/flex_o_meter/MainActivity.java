package com.example.flex_o_meter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Integer timeValue = sharedPreferences.getInt("timeValueKey", 0);
        Log.d("debug1", String.valueOf(timeValue));

        int hours = timeValue / 60;
        int minutes = timeValue % 60;
        TextView show_flex_time = (TextView) findViewById(R.id.tV_flex_time);
        show_flex_time.setText((String.valueOf(hours)) + "h and " + String.valueOf(minutes) + "min");


        //Buttons:
        Button button_minus5 = (Button) findViewById(R.id.btn_minus5);
        Button button_plus5 = (Button) findViewById(R.id.btn_plus5);
        Button button_minus15 = (Button) findViewById(R.id.btn_minus15);
        Button button_plus15 = (Button) findViewById(R.id.btn_plus15);
        Button button_minus30 = (Button) findViewById(R.id.btn_minus30);
        Button button_plus30 = (Button) findViewById(R.id.btn_plus30);

        Button button_reset = (Button) findViewById(R.id.btn_reset);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer temp_timeValue = sharedPreferences.getInt("timeValueKey", 0);
                int btn_val;
                btn_val = Integer.parseInt((String) view.getTag());
                if(btn_val == 0){
                    Log.d("debug1", "value is 0..");

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Are you sure you want to proceed and reset saved time?");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    editor.putInt("timeValueKey", 0);
                                    editor.commit();
                                    int hours = sharedPreferences.getInt("timeValueKey", 0) / 60;
                                    int minutes = sharedPreferences.getInt("timeValueKey", 0) % 60;
                                    show_flex_time.setText(String.valueOf(hours) + "h and " + String.valueOf(minutes) + "min");
                                    Toast.makeText(getApplicationContext(),"Time Reset",Toast.LENGTH_SHORT).show();
                                }
                            });

                    builder.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(),"Reset Canceled...",Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();


                }
                else {
                    editor.putInt("timeValueKey", temp_timeValue + btn_val);
                    editor.commit();
                    Log.d("debug1", "Value is " + String.valueOf(sharedPreferences.getInt("timeValueKey", 0)));
                    int hours = sharedPreferences.getInt("timeValueKey", 0) / 60;
                    int minutes = sharedPreferences.getInt("timeValueKey", 0) % 60;
                    show_flex_time.setText((String.valueOf(hours)) + "h and " + String.valueOf(minutes) + "min");
                }
            }
        };
        button_minus5.setOnClickListener(onClickListener);
        button_plus5.setOnClickListener(onClickListener);
        button_minus15.setOnClickListener(onClickListener);
        button_plus15.setOnClickListener(onClickListener);
        button_minus30.setOnClickListener(onClickListener);
        button_plus30.setOnClickListener(onClickListener);
        button_reset.setOnClickListener(onClickListener);

    }
}