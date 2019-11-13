package com.example.movie_h_b.moviemood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movie_h_b.moviemood.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();
    }

}
