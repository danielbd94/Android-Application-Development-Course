package com.example.ex7;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ShareViewModel extends AndroidViewModel {

    //countries observer
    private final MutableLiveData<ArrayList<Country>> countries;
    //position observer
    private final MutableLiveData<Integer> itemSelected;
    private static ArrayList<String> remove_countries;
    private static String filePath;
    public static final String FILE_NAME="remove_countries.txt";
    private ArrayList<Country> countriesTemp;
    public Context context;

    public ShareViewModel(@NonNull Application application){
        super(application);
        countries = new MutableLiveData<ArrayList<Country>>();
        itemSelected = new MutableLiveData<Integer>();
        this.context = application.getApplicationContext();
        init(context);
    }

    private void init(Context context) {

        // Initialize contacts
        country(CountryXMLParser.parseCountries(context));
        itemSelected.setValue(-1);
        filePath = context.getFilesDir().getAbsolutePath();
        countriesTemp =new ArrayList<Country>();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean remember = pref.getBoolean("check_box_remember_removed", false);

        /*
        //raw-file
        remove_countries = new ArrayList<String>(Arrays.asList(readFile().split("\n")));
        //end-raw-file
         */

        //shared-preferences
        remove_countries = new ArrayList<>();
        for(Map.Entry<String,?> entry : pref.getAll().entrySet()){
            if(entry.getValue() instanceof String){
                remove_countries.add(String.valueOf(entry.getValue()));
            }
        }
        //end-shared-preferences

        if(remember == true)
        {
            for(Country country : countries.getValue())
            {
                if(!remove_countries.contains(country.getName()))
                    countriesTemp.add(country);
            }

            countries.setValue(countriesTemp);
        }

        else{
            //raw-file
            try{
                FileOutputStream writer = new FileOutputStream(filePath+ File.separator+FILE_NAME);
                writer.write(("".getBytes()));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //end-raw-file

            //shared-preferences
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            //end-shared-preferences
        }

    }

    public void country(ArrayList<Country> item) {
        countries.setValue(item);
    }

    public LiveData<ArrayList<Country>> getCountry() {
        return countries;
    }

    public void select(Integer item) {
        itemSelected.setValue(item);
    }

    public LiveData<Integer> getSelected() {
        return itemSelected;
    }

    private String readFile(){
        StringBuilder text =null;
        String line;

        //Get the text file
        File file = new File(filePath, File.separator+FILE_NAME);

        try {
            if(!file.exists())
                file.createNewFile();

            //read text from file
            InputStream inputStream = new FileInputStream(file);
            text = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null){
                text.append(line);
                text.append('\n');
            }

            inputStream.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    //function for raw file
    public void writeData(String data){
        File directory = new File(filePath);
        if(!directory.exists())
            directory.mkdir();
        File newFile = new File(filePath, File.separator+FILE_NAME);
        try{
            if(!newFile.exists())
                newFile.createNewFile();

            FileOutputStream fout = new FileOutputStream(newFile, true);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fout);
            outputWriter.write(data+"\n");
            outputWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

