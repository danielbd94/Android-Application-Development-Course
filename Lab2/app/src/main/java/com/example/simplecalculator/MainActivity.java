package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText ed1;
    public EditText ed2;
    public TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        result = findViewById(R.id.result);

    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt("savedResult",Integer.valueOf(result.getText().toString()));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(String.valueOf(savedInstanceState.getInt("savedResult")));
    }

    public void plus(View view) {
        Context context = getApplicationContext();
        CharSequence text = "You must fill both operands!";
        int duration = Toast.LENGTH_SHORT;

        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString())||(TextUtils.isEmpty(ed1.getText().toString())&&TextUtils.isEmpty(ed2.getText().toString())))
            Toast.makeText(context,text,duration).show();
       else result.setText(String.valueOf(Integer.valueOf(ed1.getText().toString())+Integer.valueOf(ed2.getText().toString())));
    }

    public void minus(View view) {
        Context context = getApplicationContext();
        CharSequence text = "You must fill both operands!";
        int duration = Toast.LENGTH_SHORT;

        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString())||(TextUtils.isEmpty(ed1.getText().toString())&&TextUtils.isEmpty(ed2.getText().toString())))
            Toast.makeText(context,text,duration).show();
        else result.setText(String.valueOf(Integer.valueOf(ed1.getText().toString())-Integer.valueOf(ed2.getText().toString())));
    }

    public void mul(View view) {
        Context context = getApplicationContext();
        CharSequence text = "You must fill both operands!";
        int duration = Toast.LENGTH_SHORT;

        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString())||(TextUtils.isEmpty(ed1.getText().toString())&&TextUtils.isEmpty(ed2.getText().toString())))
            Toast.makeText(context,text,duration).show();
        else result.setText(String.valueOf(Integer.valueOf(ed1.getText().toString())*Integer.valueOf(ed2.getText().toString())));
    }

    public void div(View view) {
        Context context = getApplicationContext();
        CharSequence text1 = "You must fill both operands!";
        CharSequence text2 = "Divide by 0 is not allowed!";
        int duration = Toast.LENGTH_SHORT;

        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString())||(TextUtils.isEmpty(ed1.getText().toString())&&TextUtils.isEmpty(ed2.getText().toString())))
            Toast.makeText(context,text1,duration).show();
        else if(ed2.getText().toString().equals("0"))
            Toast.makeText(context,text2,duration).show();
        else result.setText(String.valueOf(Integer.valueOf(ed1.getText().toString())/Integer.valueOf(ed2.getText().toString())));
    }
}