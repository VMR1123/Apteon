package com.example.digiitplay.ShapeShiftScoreHistory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.digiitplay.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ShapeShiftScoreHistoryActivity extends AppCompatActivity {

    private String[] tabs = {"Normal", "Challenging"};

    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_shift_score_history);

//        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager2);

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(ShapeShiftScoreHistoryActivity.this);

        viewPager2.setAdapter(viewPagerFragmentAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> tab.setText(tabs[position])).attach();
    }
}