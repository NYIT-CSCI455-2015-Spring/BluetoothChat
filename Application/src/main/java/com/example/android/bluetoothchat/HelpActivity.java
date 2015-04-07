package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        OnButtonClickListener();

    }

    private void OnButtonClickListener() {
        Button UserGuide = (Button) findViewById(R.id.userguide);
        final Button FeedBack = (Button) findViewById(R.id.feedback);
        Button FAQs = (Button) findViewById(R.id.faqs);

        //Camera Button
        Button Camera = (Button) findViewById(R.id.camera);
        Camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraintent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(cameraintent);
            }
        });


        //Userguide Button
        UserGuide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userguideintent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("http://nyit-csci455-2015-spring.github.io/BluetoothChat/"));
                startActivity(userguideintent);

                TextView welcomemessage = (TextView) findViewById(R.id.helppagemessage);
                welcomemessage.setText("One Moment Please");

            }
        });

        //Feedback Button
        FeedBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedbackintent = new Intent(HelpActivity.this, FeedbackActivity.class);
                startActivity(feedbackintent);

                TextView welcomemessage = (TextView) findViewById(R.id.helppagemessage);
                welcomemessage.setText("One Moment Please");


            }
        });
        //FAQs Button
        FAQs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faqsintent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://github.com/NYIT-CSCI455-2015-Spring/BluetoothChat"));
                startActivity(faqsintent);

                TextView welcomemessage = (TextView) findViewById(R.id.helppagemessage);
                welcomemessage.setText("One Moment Please");

            }
        });

        //Asks user if they need more time if they hold on the buttonf or more than one second

        UserGuide.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView userguidemessage = (TextView) findViewById(R.id.helppagemessage);
                        return true;

                    }

                }
        );
        //Asks user if they need more time if they hold on the buttonf or more than one second
        FeedBack.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView feedbackmessage = (TextView) findViewById(R.id.helppagemessage);
                        return true;

                    }

                }
        );
        //Asks user if they need more time if they hold on the buttonf or more than one second
        FAQs.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        TextView faqsmessage = (TextView) findViewById(R.id.helppagemessage);
                        return true;

                    }

                }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
