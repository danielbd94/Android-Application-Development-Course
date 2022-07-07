package com.example.lab8;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class detailsFrag extends Fragment implements OnClickListener {
    detailsFragListener listener;
    private TextView detailsFragment;
    SVM model;

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (detailsFragListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'detailsFragListener'");
        }
        super.onAttach(context);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(SVM.class);
        detailsFragment = (TextView)view.findViewById(R.id.detailsFragment);
        //get information about changes in position
        if(model.getSelected().getValue() != -1) {
            model.getSelected().observe(getViewLifecycleOwner(), item -> {
                if(model.getSelected().getValue() != -1)
                    detailsFragment.setText(model.getCountry().getValue().get(model.getSelected().getValue()).getDetails());
            });

            //get information about changes in Country list
            model.getCountry().observe(getViewLifecycleOwner(), item -> {
                detailsFragment.setText(model.getCountry().getValue().get(model.getSelected().getValue()).getDetails().trim());
            });
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detailsfrag, container, false);
    }

    @Override
    public void onClick(View v) {

    }


    public interface detailsFragListener{
        //public void OnClickEvent();
    }
}