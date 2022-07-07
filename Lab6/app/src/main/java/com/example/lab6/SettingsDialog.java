package com.example.lab6;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SettingsDialog extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    public static String PROG = "progress";
    private SettingsListener mListener;
    private SeekBar mSeekBar;
    private TextView exampleValueText;

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        exampleValueText.setText(String.format("%."+i+"f", 123.0));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public interface SettingsListener {
        void onSeekBarChanged(int progress);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.SettingsDialog);
        View seekBarView = getActivity().getLayoutInflater().inflate(R.layout.slider, null);
        mSeekBar = seekBarView.findViewById(R.id.seekBar);
        exampleValueText = seekBarView.findViewById(R.id.exampleValue);
        mSeekBar.setOnSeekBarChangeListener(this);

        // Get precision and update seekBar
        Bundle args = getArguments();
        if (args != null) {
            int prog = args.getInt(PROG);
            if (0 <= prog && prog <= 5)
                mSeekBar.setProgress(prog);
        }

        // Inflate and set the layout for the dialog
        builder.setView(seekBarView)
                .setTitle("Set the numbers precision")
                .setIcon(R.drawable.icon)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mListener.onSeekBarChanged(mSeekBar.getProgress());
                                dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                );
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragA.FragAListener) {
            mListener = (SettingsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SettingsListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
