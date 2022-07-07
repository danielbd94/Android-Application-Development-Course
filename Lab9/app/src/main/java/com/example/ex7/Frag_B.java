package com.example.ex7;

import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Frag_B extends Fragment implements OnClickListener {


    FragBListener listener;
    private TextView detailsFragment;
    ShareViewModel model;

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (FragBListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'FragAListener'");
        }
        super.onAttach(context);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        detailsFragment = (TextView)view.findViewById(R.id.detailsFragment);
        //get information about changes in position
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            detailsFragment.setText(model.getCountry().getValue().get(model.getSelected().getValue()).getDetails());
        });

        //get information about changes in Country list
        model.getCountry().observe(getViewLifecycleOwner(), item -> {
            detailsFragment.setText(model.getCountry().getValue().get(model.getSelected().getValue()).getDetails());
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_b, container, false);

    }

    @Override
    public void onClick(View v) {

    }


    public interface FragBListener{
        //public void OnClickEvent();
    }
}