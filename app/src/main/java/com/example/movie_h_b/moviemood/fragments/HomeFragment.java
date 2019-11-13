package com.example.movie_h_b.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.movie_h_b.moviemood.R;
import com.example.movie_h_b.moviemood.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    public static final String API_KEY = "bad1aede729018eb5ef310ff78c97a62";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initViews(view);
        tabLayout.bringToFront();

        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPagerAdapter.AddFragment(new TopRatedFragment(), getResources().getString(R.string.topRated));
        viewPagerAdapter.AddFragment(new ComingSoonFragment(), getResources().getString(R.string.comingSoon));
        viewPagerAdapter.AddFragment(new TVSearialsFragment(), getResources().getString(R.string.tvSerials));

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    public void initViews(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
    }
}
