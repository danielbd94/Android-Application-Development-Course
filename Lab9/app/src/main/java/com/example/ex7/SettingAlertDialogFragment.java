package com.example.ex7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceFragmentCompat;

public class SettingAlertDialogFragment extends PreferenceFragmentCompat implements countriesAdapter.AdapterListener{

    //EditNameDialogListener listener;

    public SettingAlertDialogFragment(){
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);//inflsate
    }

    /*
    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (EditNameDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'EditNameDialogListener'");
        }
        super.onAttach(context);
    }

     */

  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

   */

    @Override
    public void changeFragment() {

    }

}
