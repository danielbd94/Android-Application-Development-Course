package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements countryFrag.countryListener, detailsFrag.detailsFragListener {
    public static final String MYTAG = "MYTAG";
    int id = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detailsFrag dfragB = (detailsFrag) getSupportFragmentManager().findFragmentByTag("FRAGB");

        // Decide if to add dynamically fragmentB depend if Landscape or portrait
        if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            Log.i("#0", "here");
            if (dfragB != null) {
                Log.i("#1", "here");
                getSupportFragmentManager().beginTransaction()
                        .remove(dfragB)
                        .commit();
                getSupportFragmentManager().executePendingTransactions();
            }
            //} else {
            if (id != -1) {
                Log.i("#2", "here");
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragContainer, detailsFrag.class, null, "FRAGB")
                        .addToBackStack("BBB")
                        .commit();
            }
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public void change(int id) {
        this.id = id;
        detailsFrag dfragB = null;
        dfragB = (detailsFrag) getSupportFragmentManager().findFragmentByTag("FRAGB");
        if (dfragB != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(dfragB)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }

        if (id != -1) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragContainer, detailsFrag.class, null, "FRAGB")
                    .addToBackStack("BBB")
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }
    }
}
