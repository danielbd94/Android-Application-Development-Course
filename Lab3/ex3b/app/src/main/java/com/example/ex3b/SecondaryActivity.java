package com.example.ex3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity {
    public EditText ed1,ed2;
    public RadioButton rb1,rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        ed1=findViewById(R.id.ed1); //for first name
        ed2=findViewById(R.id.ed2); //for last name
        rb1=findViewById(R.id.rbMale); //male radio button
        rb2=findViewById(R.id.rbFemale); //female radio button
    }

    public void SendBack(View view) { //when clicking send back button
        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString())||(TextUtils.isEmpty(ed1.getText().toString())&&TextUtils.isEmpty(ed2.getText().toString())))
            Toast.makeText(this,"You must fill both first & last name",Toast.LENGTH_SHORT).show(); //check both first and last name are full with text. Toast if not.
        else if (!rb1.isChecked()&&!rb2.isChecked())
            Toast.makeText(this, "Gender must be selected to continue", Toast.LENGTH_SHORT).show(); //check gender selected. Toast if not.
        else {
            Intent intent = new Intent(this,MainActivity.class); //create new Explicit Intent because we know the result should be send to the MainActivity
            intent.putExtra("FirstName", ed1.getText().toString()); //push the user answers to the "Extras" area
            intent.putExtra("LastName", ed2.getText().toString());
            intent.putExtra("Gender", rb1.isChecked());
            setResult(RESULT_OK, intent); //set intent result for "ok"
            finish(); //close correct activity
        }
    }
}