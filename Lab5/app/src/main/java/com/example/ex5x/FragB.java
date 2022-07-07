package com.example.ex5x;

//import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragB extends Fragment implements SeekBar.OnSeekBarChangeListener {
	TextView tvValue;
	FragBListener listener;
	static int myInt=0;
	public SeekBar seekbar;
	public TextView result,exampleValueText;
	public double resultText = 0;

	@Override
	public void onAttach(@NonNull Context context) {
		try{
			this.listener = (FragBListener)context;
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					getActivity().getClass().getName() +
					" must implements the interface 'FragBListener'");
		}

		super.onAttach(context);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_b, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		this.tvValue= (TextView) view.findViewById(R.id.result);
		tvValue.setText(""+myInt);
		result = view.findViewById(R.id.result);
		// Seekbar values
		exampleValueText = view.findViewById(R.id.exampleValue);
		seekbar = view.findViewById(R.id.seekBar);
		seekbar.setMax(5);
		seekbar.setOnSeekBarChangeListener(this); // Define listener using "this" because MainActivity implements SeekBar interface
		super.onViewCreated(view, savedInstanceState);
	}

	//the activity informs fragB about new click in fragA
	public void onNewClick(double result){
		System.out.println("Your message");
		Log.println(Log.DEBUG,"debug", "Your message to print");
		resultText = result;
		setResultText(seekbar.getProgress());
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
		exampleValueText.setText(String.format("%."+i+"f", 123.0));
		if(result.getText().toString().equals("")) return;
		setResultText(i);
	}

	private void setResultText(int progress) {
		result.setText(String.format("%."+progress+"f", resultText));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}


	public interface FragBListener{
		//put here methods you want to utilize to communicate with the hosting activity
	}

}
