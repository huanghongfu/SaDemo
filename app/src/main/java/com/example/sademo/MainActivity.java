package com.example.sademo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Fragment mPreFragment;
    private DemoFragment demoFragment1;
    private DemoFragment demoFragment2;
    private DemoFragment demoFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         demoFragment1 = DemoFragment.newInstance();
         demoFragment2 = DemoFragment.newInstance();
         demoFragment3 = DemoFragment.newInstance();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherActivity.start(MainActivity.this);
            }
        });

        RadioGroup rg = findViewById(R.id.rg_container);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        switchFragment(demoFragment1);
                        break;
                    case R.id.rb2:
                        switchFragment(demoFragment2);
                        break;
                    case R.id.rb3:
                        switchFragment(demoFragment3);
                        break;
                    default:
                }
            }
        });

        switchFragment(demoFragment1);
    }


    public void switchFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fl_container, fragment);
        }

        if (mPreFragment == null) {
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.hide(mPreFragment).commit();
        }

        mPreFragment = fragment;
    }
}
