package com.example.digiitplay.Dashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digiitplay.Dashboard.GamesFragment;
import com.example.digiitplay.Dashboard.LeaderboardsFragment;
import com.example.digiitplay.Dashboard.ProfileFragment;
import com.example.digiitplay.R;
import com.example.digiitplay.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new GamesFragment());
        binding.bottomNavigation.setSelectedItemId(R.id.games);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.games:
                    replaceFragment(new GamesFragment());
                    break;
                case R.id.leaderboards:
                    replaceFragment(new LeaderboardsFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_layout, fragment);
        fragmentTransaction.commit();
    }
}
