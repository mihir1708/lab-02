package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // declaring the elements to be used
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCity;
    Button deleteCity;
    Button confirmCity;
    EditText inputCity;
    LinearLayout bottom;
    // setting the initial selected index to an invalid position
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    // initializing the elements from the activity_main xml file
        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        deleteCity = findViewById(R.id.delete_city);
        confirmCity = findViewById(R.id.confirm_city);
        inputCity = findViewById(R.id.input_city);
        bottom = findViewById(R.id.bottom);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
    // adapter links the xml elements to the data list
        cityAdapter = new ArrayAdapter<>(this, R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);
    // sets the selected index to the position of item clicked
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                index = position;
            }
        });
    // makes the bottom bar visible when we press add button
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                bottom.setVisibility(View.VISIBLE);
                inputCity.requestFocus();
                inputCity.setText("");
            }
        });
    // adds the input city to the list view and makes the bottom bar disappear
        confirmCity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String name = inputCity.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter City Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataList.add(name);
                cityAdapter.notifyDataSetChanged();
                bottom.setVisibility(View.GONE);
                inputCity.setText("");
                index= -1;
                cityList.clearChoices();

            }
        });
    // deletes the selected city from list view
        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (index >= 0 && index < dataList.size()) {
                    String deletedCity = dataList.remove(index);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Deleted " + deletedCity, Toast.LENGTH_SHORT).show();
                    index = -1;
                    cityList.clearChoices();
                } else {
                    Toast.makeText(MainActivity.this, "First Select a City", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}