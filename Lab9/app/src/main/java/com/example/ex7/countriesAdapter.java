package com.example.ex7;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class countriesAdapter extends RecyclerView.Adapter<countriesAdapter.ViewHolder> {


    ShareViewModel model;
    private int selected_position = RecyclerView.NO_POSITION;
    private AdapterListener adapterListener;

    public countriesAdapter(AdapterListener adapterListener, ShareViewModel model) {
        this.adapterListener = adapterListener;
        this.model = model;
        if( this.model.getSelected().getValue() != null) {
            selected_position = this.model.getSelected().getValue();
            notifyItemChanged(selected_position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView countryName;
        public TextView countryPopulation;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            countryName = (TextView)itemView.findViewById(R.id.countryName);
            countryPopulation = (TextView)itemView.findViewById(R.id.countryPopulation);
        }

        public void fillData(@NonNull countriesAdapter.ViewHolder holder, final int position){
            //fill the item, gets the exact position
            Country country = model.getCountry().getValue().get(position);
            Context context = holder.imageView.getContext();
            //Get images from drawable, this is the only option
            int id = context.getResources().getIdentifier(country.getFlag(),"drawable",context.getPackageName());
            holder.imageView.setImageResource(id);
            holder.countryName.setText(country.getName());
            holder.countryPopulation.setText(country.getShorty());
            holder.itemView.setOnLongClickListener((view)-> {

                int pos = position;

                /*
                //raw-file
                model.writeData(model.getCountry().getValue().get(position).getName());
                //end-raw-file
                */

                //shared-preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(model.context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(model.getCountry().getValue().get(position).getName(), model.getCountry().getValue().get(position).getName());
                editor.commit();
                //end-shared-preferences

                model.getCountry().getValue().remove(position);
                model.country((model.getCountry().getValue()));
                //deletion in landscape mode
                if ((position == model.getCountry().getValue().size() && model.getCountry().getValue().size() > 0)) { //if the country is in the lowest position
                    pos--;
                }

                //if (pos < model.getSelected().getValue()) { //the selected country is lower than the country that we want to delete
                  //  model.select(model.getSelected().getValue() - 1);
               // }

                if (position == model.getCountry().getValue().size() && model.getCountry().getValue().size() == 0) { //if the last country deleted
                    adapterListener.changeFragment();
                }
                notifyDataSetChanged();
                return true;
            });

            holder.itemView.setOnClickListener((view)->{


                notifyItemChanged(selected_position);
                selected_position = getLayoutPosition();
                notifyItemChanged(selected_position);
                model.select(selected_position);
                adapterListener.changeFragment();

                    }
            );

        }
    }


    @NonNull
    @Override
    public countriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View countryView = inflater.inflate(R.layout.countries_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(countryView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull countriesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        String color_string = "#14D8D8";
        int myColor = Color.parseColor(color_string);
        holder.fillData(holder,position);
        holder.itemView.setSelected(selected_position == position);
        if(selected_position == position)
            holder.itemView.setBackgroundColor(Color.WHITE);
        else
            holder.itemView.setBackgroundColor(myColor);
        }
    @Override
    public int getItemCount() {
        return model.getCountry().getValue().size();
    }

    public interface AdapterListener {
        public void changeFragment();
    }


}
