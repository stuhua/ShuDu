package com.ss.ShuDu3;

import android.app.Activity;
import android.os.Bundle;

public class ShuDu3Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(new MyView(this));
    }
}