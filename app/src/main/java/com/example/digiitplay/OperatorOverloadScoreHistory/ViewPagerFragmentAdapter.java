package com.example.digiitplay.OperatorOverloadScoreHistory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] tabs = {"Easy", "Medium", "Hard", "Hard+", "All"};
    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new EasyFragment();

            case 1:
                return new MediumFragment();

            case 2:
                return new HardFragment();

            case 3:
                return new HardPlusFragment();

            case 4:
                return new AllFragment();
        }
        return new EasyFragment();
    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}
