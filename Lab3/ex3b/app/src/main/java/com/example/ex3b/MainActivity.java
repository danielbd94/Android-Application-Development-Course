package com.example.ex3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView tw;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tw=findViewById(R.id.textView); //for change the TextView text later
        button=findViewById(R.id.button); //for change the Button text later
    }

    public void StartRegister(View view) {
        Intent intent = new Intent(this,SecondaryActivity.class); //create new Explicit Intent that will request the service of the secondary activity.
        startActivityForResult(intent,10); //for result because we are expect to receive a result from the targeted activity (in our case the name and the gender)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 10) { //check that the result is match our request
            if (resultCode == RESULT_OK) {
                if (intent.getExtras().getBoolean("Gender")) //get field Gender from the "Extras" - true = male , false = female. change the text and the button accordingly
                    tw.setText("Welcome back Mr. " + intent.getExtras().getString("FirstName") + ", " + intent.getExtras().getString("LastName"));
                else  tw.setText("Welcome back Ms. " + intent.getExtras().getString("FirstName") + ", " + intent.getExtras().getString("LastName"));
                button.setText("again...");
            }
        }
    }
}