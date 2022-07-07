package com.example.ex7;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class Frag_A extends Fragment implements countriesAdapter.AdapterListener {

    private ShareViewModel model;
    FragAListener listener;
    countriesAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (FragAListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'FragAListener'");
        }
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_a, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Lookup the recyclerview in activity layout
        RecyclerView rvCountries = (RecyclerView) view.findViewById(R.id.rvCountry);
        // Initialize contacts
        model = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        //get information about changes in position
            model.getSelected().observe(getViewLifecycleOwner(), item -> {
            adapter.notifyDataSetChanged();
            });

        //get information about changes in Country list
            model.getCountry().observe(getViewLifecycleOwner(), item -> {
                adapter.notifyDataSetChanged();
            });

        // Create adapter passing in the sample user data
        adapter = new countriesAdapter(Frag_A.this,model);
        // Attach the adapter to the recyclerview to populate items
        rvCountries.setAdapter(adapter);
        // Set layout manager to position the items
        rvCountries.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    @Override
    public void changeFragment() {
        listener.change();
    }

    interface FragAListener {
        public void change();

    }

}
