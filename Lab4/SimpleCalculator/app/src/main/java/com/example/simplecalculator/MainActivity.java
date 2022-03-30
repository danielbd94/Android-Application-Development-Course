package com.example.simplecalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener { //Implementation of OnSeekBarChangeListener in MainActivity
    public EditText ed1, ed2;
    public TextView result, example;
    public Button plus, minus, mul, div, clear;
    public SeekBar sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding the sublayout using ‘layout-inflater’
        ViewGroup parentLayout=findViewById(R.id.MainLayout);
        View child=getLayoutInflater().inflate(R.layout.seekbar_sublayout,parentLayout,false);
        parentLayout.addView(child);
        //

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        result = findViewById(R.id.result);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        mul = findViewById(R.id.multiply);
        div = findViewById(R.id.divide);
        clear = findViewById(R.id.clear);
        sk = findViewById(R.id.seekBar);
        example = findViewById(R.id.example);


        ed1.addTextChangedListener(new textChanged()); //define listener to the callback method "textChanged"
        ed2.addTextChangedListener(new textChanged()); //define listener to the callback method "textChanged"

        clear.setOnClickListener(new View.OnClickListener() { //Anonymous ‘onClickListener’ listener
            @Override
            public void onClick(View view) {
                ed1.setText("");
                ed2.setText("");
                result.setText("");
            }
        });

        sk.setOnSeekBarChangeListener(this); //define listener using "this" because MainActivity implements SeekBar interface
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (result.getText().toString().length() > 0) {
            outState.putFloat("savedResult", Float.valueOf(result.getText().toString()));
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (!TextUtils.isEmpty(ed1.getText().toString()) || !TextUtils.isEmpty(ed2.getText().toString()))
            result.setText(String.valueOf(savedInstanceState.getFloat("savedResult")));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (i) {
            case 0:
                example.setText("Example:123");
                break;
            case 1:
                example.setText("Example:123.0");
                break;
            case 2:
                example.setText("Example:123.00");
                break;
            case 3:
                example.setText("Example:123.000");
                break;
            case 4:
                example.setText("Example:123.0000");
                break;
            case 5:
                example.setText("Example:123.00000");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class textChanged implements TextWatcher { //implement of the class TextWatcher (member class) that ed1,ed2 will use when the text changes

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (TextUtils.isEmpty(ed1.getText().toString()) || TextUtils.isEmpty(ed2.getText().toString()) || (TextUtils.isEmpty(ed1.getText().toString()) && TextUtils.isEmpty(ed2.getText().toString()))) {
                plus.setEnabled(false);
                minus.setEnabled(false);
                mul.setEnabled(false);
                div.setEnabled(false);
            } else {
                plus.setEnabled(true);
                minus.setEnabled(true);
                mul.setEnabled(true);
                div.setEnabled(true);
            }
            if (ed2.getText().toString().equals("0")) {
                plus.setEnabled(true);
                minus.setEnabled(true);
                mul.setEnabled(true);
                div.setEnabled(false);
            }
            if (!TextUtils.isEmpty(ed1.getText().toString()) || !TextUtils.isEmpty(ed2.getText().toString()) || !TextUtils.isEmpty(result.getText().toString()) || !(TextUtils.isEmpty(ed1.getText().toString()) && TextUtils.isEmpty(ed2.getText().toString()))) {
                clear.setEnabled(true);
            } else clear.setEnabled(false);
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    public void plus(View view) {
        switch (sk.getProgress()) {
            case 0:
                result.setText(String.format("%.0f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
            case 1:
                result.setText(String.format("%.1f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
            case 2:
                result.setText(String.format("%.2f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
            case 3:
                result.setText(String.format("%.3f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
            case 4:
                result.setText(String.format("%.4f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
            case 5:
                result.setText(String.format("%.5f", Float.valueOf(ed1.getText().toString()) + Float.valueOf(ed2.getText().toString())));
                break;
        }
    }

    public void minus(View view) {
        switch (sk.getProgress()) {
            case 0:
                result.setText(String.format("%.0f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
            case 1:
                result.setText(String.format("%.1f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
            case 2:
                result.setText(String.format("%.2f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
            case 3:
                result.setText(String.format("%.3f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
            case 4:
                result.setText(String.format("%.4f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
            case 5:
                result.setText(String.format("%.5f", Float.valueOf(ed1.getText().toString()) - Float.valueOf(ed2.getText().toString())));
                break;
        }
    }

    public void div(View view) {
        switch (sk.getProgress()) {
            case 0:
                result.setText(String.format("%.0f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
            case 1:
                result.setText(String.format("%.1f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
            case 2:
                result.setText(String.format("%.2f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
            case 3:
                result.setText(String.format("%.3f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
            case 4:
                result.setText(String.format("%.4f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
            case 5:
                result.setText(String.format("%.5f", Float.valueOf(ed1.getText().toString()) / Float.valueOf(ed2.getText().toString())));
                break;
        }
    }

    public void mul(View view) {
        switch (sk.getProgress()) {
            case 0:
                result.setText(String.format("%.0f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
            case 1:
                result.setText(String.format("%.1f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
            case 2:
                result.setText(String.format("%.2f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
            case 3:
                result.setText(String.format("%.3f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
            case 4:
                result.setText(String.format("%.4f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
            case 5:
                result.setText(String.format("%.5f", Float.valueOf(ed1.getText().toString()) * Float.valueOf(ed2.getText().toString())));
                break;
        }
    }
}