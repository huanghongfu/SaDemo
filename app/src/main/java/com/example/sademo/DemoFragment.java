package com.example.sademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imac
 * @date 19/3/19
 */
public class DemoFragment extends Fragment {


    public static DemoFragment newInstance() {

        Bundle args = new Bundle();

        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demo, null);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(SubDemoFragment.newInstance());
        fragments.add(SubDemoFragment.newInstance());
        final String[] titles = {"SUB1","SUB2"};

        viewPager.setAdapter(new CommFragmentPageAdapter(getChildFragmentManager(),fragments,titles));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }


}
