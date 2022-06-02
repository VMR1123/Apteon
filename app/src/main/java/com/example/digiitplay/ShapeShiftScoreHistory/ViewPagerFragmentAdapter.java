package com.example.digiitplay.ShapeShiftScoreHistory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] tabs = {"Normal", "Challenging"};
    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NormalFragment();

            case 1:
                return new ChallengingFragment();

        }
        return new NormalFragment();
    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}