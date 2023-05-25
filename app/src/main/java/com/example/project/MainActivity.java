package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22yifwa";

    ArrayList<RecyclerViewItem> listOfMountains= new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.start_second_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        adapter = new RecyclerViewAdapter(this, listOfMountains, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        new JsonTask(this, this).execute(JSON_URL);//Hämta data med URL eller file//
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }

    @Override
    public void onPostExecute(String json) {

        Log.d("MainActivity", json);
        Gson gson = new Gson(); // Create GSON object to perform marshall operations//
        Type type = new TypeToken<List<Mountain>>() {}.getType();// Unmarshall JSON -> list of objects
        List<Mountain> jsonMountains = gson.fromJson(json, type);
        for(Mountain m : jsonMountains){
            Log.d("MainActivityMountain", m.getName()+m.getLocation()+m.getCost());
            listOfMountains.add(new RecyclerViewItem(m.getName()+"  "+m.getLocation()+"  "+m.getCost()));
        }
        adapter.notifyDataSetChanged();
    }

}
