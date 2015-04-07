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

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.common.logger.Log;

public class MainActivity extends FragmentActivity implements DummyFragment.OnFragmentInteractionListener {

    public static final String TAG = "MainActivity";
    Fragment fragment;
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, Utils.getTimeStamp() + " Main Activity created.");
        boolean isEmulator = Build.FINGERPRINT.contains("generic");

        if (isEmulator) {
            Log.d(TAG, "It's an emulator! Starting DummyFragment.");
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
            fragment = new DummyFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        } else if (savedInstanceState == null) {
            Log.d(TAG, "No saved instance, starting new fragment.");
            fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        } else if (savedInstanceState.getString("chatFragment") != null) {
            Log.d(TAG, "Saved instance found, restoring fragment.");
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "btcFragment");
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save bluetooth connection information here?
        if (getSupportFragmentManager().getFragments() != null) {
            getSupportFragmentManager().putFragment(outState, "btcFragment", fragment);
            Log.d(TAG, "BluetoothChatFragment instance saved." + Utils.getTimeStamp());
        }
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
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                startActivity(item.getIntent());
            case R.id.settings:
                startActivity(item.getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
