package com.example.ex7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import static com.example.ex7.R.id.fragContainer;
import static com.example.ex7.R.id.fragB;

public class MainActivity extends AppCompatActivity implements Frag_A.FragAListener, Frag_B.FragBListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Frag_B fragB = (Frag_B) getSupportFragmentManager().findFragmentByTag("FRAGB");
        if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){
            if (fragB != null) {
                getSupportFragmentManager().beginTransaction()
                        //.show(fragB)
                        .replace(R.id.fragB, new SettingAlertDialogFragment())
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragB, Frag_B.class,null, "FRAGB")
                        //	.addToBackStack(null)
                        .commit();
            }

        }

        getSupportFragmentManager().executePendingTransactions();

    }


    @Override
    public void change() {
        Frag_B fragB;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Frag_B.class, null, "FRAGB")
                    //.add(R.id.fragContainer, Frag_B.class, null,"FRAGB")
                    .addToBackStack("BBB")
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            fragB = (Frag_B) getSupportFragmentManager().findFragmentByTag("FRAGB"); //if we are in landscape orientation - find fragB in stack
        }

        else{
            try{
                fragB = (Frag_B) getSupportFragmentManager().findFragmentById(R.id.fragB);
            }
            catch (Exception e){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragB, Frag_B.class, null, "FRAGB")
                        .addToBackStack("BBB")
                        .commit();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settingButton:
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragContainer, new SettingAlertDialogFragment())
                            .addToBackStack("BBB")
                            .commit();
                    return true;
                }
                else{
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragContainer, new SettingAlertDialogFragment())
                            .addToBackStack("BBB")
                            .commit();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
