package com.example.lab6;

//import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragB extends Fragment {
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
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.frag_b, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		this.tvValue= (TextView) view.findViewById(R.id.result);
		tvValue.setText(""+myInt);
		result = view.findViewById(R.id.result);
		// Seekbar values
		exampleValueText = view.findViewById(R.id.exampleValue);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		inflater.inflate(R.menu.menu_calls_fragment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	//the activity informs fragB about new click in fragA
	public void onNewClick(double result, int progress){
		resultText = result;
		setResultText(progress);
	}

	public void setResultText(int progress) {
		result.setText(String.format("%."+progress+"f", resultText));
	}

	/*@Override
	public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
		exampleValueText.setText(String.format("%."+i+"f", 123.0));
		if(result.getText().toString().equals("")) return;
		setResultText(i);
	}*/

	public interface FragBListener{
		//put here methods you want to utilize to communicate with the hosting activity
	}

}
