package com.example.lab8;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class countryFrag extends Fragment implements ContactsAdapter.AdapterListener {

    ArrayList<Country> countries;
    countryListener listener;
    private RecyclerView recyclerView;
    private SVM model;
    ContactsAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (countryListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'countryListener'");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.country_frag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); //define the layout for the recycledView
        model = new ViewModelProvider(requireActivity()).get(SVM.class);
        //get information about changes in position
        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            adapter.notifyDataSetChanged();
        });

        //get information about changes in Country list
        model.getCountry().observe(getViewLifecycleOwner(), item -> {
            adapter.notifyDataSetChanged();
        });
        adapter = new ContactsAdapter(countryFrag.this,model);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter); //define the adapter to the recycledView
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void changeFragment(int id) {
        listener.change(id);
    }

    public interface countryListener {
        public void change(int id);
    }
}
