package com.example.sademo;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author imac
 * @date 19/3/19
 */
public class CommFragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] titles;

    private Fragment mFragment;

    public CommFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.mFragmentList = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int pos) {
        return mFragmentList.get(pos);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mFragment;
    }


}