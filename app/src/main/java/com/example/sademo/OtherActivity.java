package com.example.sademo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author imac
 * @date 19/3/19
 */
public class OtherActivity extends Activity {
    public static void start(Context context) {
        Intent starter = new Intent(context, OtherActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }
}
