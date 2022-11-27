package com.example.rater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Integer[] bg = {R.drawable.default_bg, R.drawable.top_gun, R.drawable.hulk, R.drawable.black_adam, R.drawable.iron_man, R.drawable.black_panther, R.drawable.superman};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        !! OLD SPINNER CODE !!
//        Spinner spinner = findViewById(R.id.movie_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.movies, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);


        String[] movies = new String[]{"Select Item", "Top Gun", "Hulk", "Black Adam", "Iron Man", "Black Panther", "Superman"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.drop_down_item, movies);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed);
        autoCompleteTextView.setAdapter(adapter1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String movieName = autoCompleteTextView.getText().toString();

                if (movieName.compareTo("Select Item") != 0) {
                    RateDialog rateDialog = new RateDialog(MainActivity.this, movieName);
                    rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                    rateDialog.setCancelable(false);
                    rateDialog.show();
                }

                changeWallpaper(position);
                Toast.makeText(MainActivity.this, autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeWallpaper(int moviePos) {
        Log.e("Position ", " : " + moviePos);
        findViewById(R.id.mainApp).setBackgroundResource(bg[moviePos]);
    }

//    !! OLD Spinner Code !!
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String movieName = parent.getItemAtPosition(position).toString();
//
//        if (movieName.compareTo("Select Item") != 0) {
//            RateDialog rateDialog = new RateDialog(MainActivity.this, movieName);
//            rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
//            rateDialog.setCancelable(false);
//            rateDialog.show();
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        Log.e("Nothing : ", "Nothing selected");
//    }
}