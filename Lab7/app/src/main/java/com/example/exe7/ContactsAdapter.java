package com.example.exe7;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{ //ViewHolder is the inner Class (on line 38)

    private List<Country> myCountries; //hold the countries data

    public ContactsAdapter(Context context) { //fill data using the parser
        this.myCountries = CountryXMLParser.parseCountries(context);
    }

    // Provide a direct reference to each of the views within a data item used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView flagCountry;
        public TextView txtCountry;
        public TextView txtPopulation;


        // We also create a constructor that accepts the entire item row (itemView) and does the view lookups to find each subview
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagCountry = (ImageView) itemView.findViewById(R.id.flagView);
            txtCountry =(TextView)  itemView.findViewById(R.id.txtCountry);
            txtPopulation = (TextView) itemView.findViewById(R.id.txtPopulation);
        }

        public void fillData(int position)
        {
            Country country = myCountries.get(position);
            String flag = country.getFlag();
            Uri imgUri = Uri.parse("android.resource://com.example.exe7/drawable/" + flag);
            flagCountry.setImageURI(imgUri);
            txtCountry.setText(country.getName());
            txtPopulation.setText(country.getShorty());
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() { //define long click listener for a row
                @Override
                public boolean onLongClick(View v) {
                    myCountries.remove(position); //remove country from list
                    notifyDataSetChanged(); //refresh data set
                    return false;
                }
            });

        }
    }


    /*
    Every adapter has three primary methods: onCreateViewHolder to inflate the item layout
     and create the holder, onBindViewHolder to set the view attributes based on the data
     and getItemCount to determine the number of items. We need to implement all three to
     finish the adapter:
     */

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.country, parent, false); //inflate country (one row) xml

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) { //fill a row within data on the given position

        holder.fillData(position);
    }

    @Override
    public int getItemCount() { //return the size of the data structure

        return myCountries.size();
    }
}
