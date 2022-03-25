package com.example.ex3client;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.ed1); //for phone number
        ed2=findViewById(R.id.ed2); //for URL
        ed3=findViewById(R.id.ed3); //for email address
        tv=findViewById(R.id.textView); //for the text result form ex3a or ex3b
    }

    public void register(View view) { //when clicking the register option
        Intent intent=new Intent(); //Implicit Intent because we are looking for any activity in the phone that may handles the request
        intent.setAction("com.action.register"); //the operation system will check which apps manifest have this action name
        startActivityForResult(intent,100); //expecting for a result from the other app

    }
    public void call(View view) { //when clicking the call option
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ed1.getText().toString())); //Implicit Intent with the action needed to be applied
        startActivity(intent); //no result expected so just start the activity requested
    }

    public void surf(View view) { //when clicking the surf option
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+ed2.getText().toString())); //Implicit Intent with the action needed to be applied
        startActivity(intent); //no result expected so just start the activity requested
    }

    public void email(View view) { //when clicking the email option
        Intent intent=new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+ed3.getText().toString())); //Implicit Intent with the action needed to be applied
        startActivity(intent);  //no result expected so just start the activity requested
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==100) //check that the result is match our request
            if(resultCode==RESULT_OK)
                if (intent.getExtras().getBoolean("Gender")) //get field Gender from the "Extras" - true = male , false = female. change the text and the button accordingly
                    tv.setText("Welcome back Mr. " + intent.getExtras().getString("FirstName") + ", " + intent.getExtras().getString("LastName"));
                else  tv.setText("Welcome back Ms. " + intent.getExtras().getString("FirstName") + ", " + intent.getExtras().getString("LastName"));
    }
}
