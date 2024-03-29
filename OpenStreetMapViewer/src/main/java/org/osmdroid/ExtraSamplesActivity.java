package org.osmdroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class ExtraSamplesActivity extends FragmentActivity {
    private static final String SAMPLES_FRAGMENT_TAG = "org.osmdroid.SAMPLES_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_samples);

        FragmentManager fm = this.getSupportFragmentManager();
        if (fm.findFragmentByTag(SAMPLES_FRAGMENT_TAG) == null) {
            //FragmentSamples fragmentSamples = FragmentSamples.newInstance();
            SampleCacheDownloader fragmentSamples = new SampleCacheDownloader();
            fm.beginTransaction().add(org.osmdroid.R.id.samples_container, fragmentSamples, SAMPLES_FRAGMENT_TAG).commit();
        }
    }
}
