package com.example.rater;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    // [Fix] : When clicking on already selected option again, nothing happens

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.movie_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.movies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String movieName = parent.getItemAtPosition(position).toString();

        if (movieName.compareTo("Select Item") != 0) {
            RateDialog rateDialog = new RateDialog(MainActivity.this, movieName);
            rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            rateDialog.setCancelable(false);
            rateDialog.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.e("Nothing : ", "Nothing selected");
    }
}