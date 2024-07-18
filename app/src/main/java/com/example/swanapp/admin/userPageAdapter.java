package com.example.swanapp.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.swanapp.admin.createFragment;
import com.example.swanapp.admin.deleteFragment;
import com.example.swanapp.admin.updateFragment;

public class userPageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public userPageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new createFragment();
            case 1:
                return new updateFragment();
            case 2:
                return new deleteFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
