package com.example.lab6;

//import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class FragA extends Fragment implements OnClickListener{
	FragAListener listener;
	public EditText ed1, ed2;
	public Button b1, b2, b3, b4;

	@Override
	public void onAttach(@NonNull Context context) {
		try{
			this.listener = (FragAListener)context;
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					context.getClass().getName() +
					" must implements the interface 'FragAListener'");
		}
		super.onAttach(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//setHasOptionsMenu(true);
		return inflater.inflate(R.layout.frag_a, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		view.findViewById(R.id.button3).setOnClickListener(this);
		view.findViewById(R.id.button4).setOnClickListener(this);
		view.findViewById(R.id.button5).setOnClickListener(this);
		view.findViewById(R.id.button6).setOnClickListener(this);
		ed1 = view.findViewById(R.id.ed1);
		ed2 = view.findViewById(R.id.ed2);
		b1 =  view.findViewById(R.id.button3); // "+"
		b2 =  view.findViewById(R.id.button4); // "-"
		b3 =  view.findViewById(R.id.button5); // "*"
		b4 =  view.findViewById(R.id.button6); // "/"
		TextChangeHandler textChangeHandler = new TextChangeHandler();
		ed1.addTextChangedListener(textChangeHandler); //define listener to the callback method "textChanged"
		ed2.addTextChangedListener(textChangeHandler); //define listener to the callback method "textChanged"
		super.onViewCreated(view, savedInstanceState);
	}

	/*@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		return super.onOptionsItemSelected(item);
	}*/

	@Override
	public void onClick(View v) {
		Log.println(Log.DEBUG,"debug", "here: " + v.getId());
		listener.OnClickEvent(ed1.getText().toString(), ed2.getText().toString(), v.getId());
	}

	public interface FragAListener{
		public void OnClickEvent(String p1, String p2, int operandId);
	}

	//implement of the class TextWatcher (member class) that ed1 and ed2 will use when the text changes
	private class TextChangeHandler implements TextWatcher {
		@Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}
		@Override
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}
		@Override
		public void afterTextChanged(Editable editable) {
			String ed1Text = ed1.getText().toString();
			String ed2Text = ed2.getText().toString();
			if(ed1Text.equals("") || ed2Text.equals("")) {
				b1.setEnabled(false);
				b2.setEnabled(false);
				b3.setEnabled(false);
				b4.setEnabled(false);
				return;
			}
			if (ed2Text.equals("0"))
				b4.setEnabled(false);
			else
				b4.setEnabled(true);
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
		}
	}
}
