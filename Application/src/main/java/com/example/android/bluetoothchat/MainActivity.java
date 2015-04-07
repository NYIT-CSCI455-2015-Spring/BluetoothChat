/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;
import com.example.android.common.logger.LogFragment;
import com.example.android.common.logger.LogWrapper;
import com.example.android.common.logger.MessageOnlyLogFilter;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p/>
 * For devices with display with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */

public class MainActivity extends SampleActivityBase implements DummyFragment.OnFragmentInteractionListener {

    public static final String TAG = "MainActivity";
    Fragment btcFragment = new BluetoothChatFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;


    // Whether the Log Fragment is currently shown
    //private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, Utils.getTimeStamp() + " Main Activity created.");

        if (savedInstanceState == null) {
            Log.d(TAG, "No saved instance, starting new fragment.");
            btcFragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, btcFragment);
            transaction.commit();
        } else if (savedInstanceState.getString("chatFragment") != null) {
            //Restore fragments instance
            Log.d(TAG, "Saved instance found, restoring fragment.");
            btcFragment = getSupportFragmentManager().getFragment(savedInstanceState, "btcFragment");
            transaction.replace(R.id.sample_content_fragment, btcFragment);
            transaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save bluetooth connection information here?
        getSupportFragmentManager().putFragment(outState, "btcFragment", btcFragment);
        Log.d(TAG, "onSaveInstanceState is being called" + Utils.getTimeStamp());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
//        Called after the activity has been stopped, just prior to it being started again.
//        Always followed by onStart()
    }

    @Override
    public void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem settings = menu.findItem(R.id.settings).setIntent(new Intent(this, SettingsActivity.class));
        MenuItem help = menu.findItem(R.id.help).setIntent(new Intent(this, HelpActivity.class));
        //MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        /*logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);*/

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.menu_toggle_log:
            //mLogShown = !mLogShown;
            //ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
                /*if (mLogShown) {
                    output.setDisplayedChild(1);
                } else {
                    output.setDisplayedChild(0);
                }
                supportInvalidateOptionsMenu();*/
            //return true;
            case R.id.help:
                startActivity(item.getIntent());
            case R.id.settings:
                startActivity(item.getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Create a chain of targets that will receive log data
     */
    @Override
    public void initializeLogging() {
        // Wraps Android's native log framework.
        //LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        //Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        //MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        //logWrapper.setNext(msgFilter);

        /*// On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());*/

        //Log.i(TAG, "Ready");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
