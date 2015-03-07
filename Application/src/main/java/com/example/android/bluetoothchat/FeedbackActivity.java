package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class FeedbackActivity extends Activity {

    Button Send;//Send Button
    EditText SendTo, Subject,Message;//Edit Text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Feedback Page
        Send = (Button) findViewById(R.id.sendto);
        SendTo = (EditText) findViewById(R.id.EditSendTo);
        Subject = (EditText) findViewById(R.id.subjectline);
        Message =  (EditText) findViewById(R.id.messageline);

        Send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendTo, subject, message;
                sendTo = SendTo.getText().toString();
                subject = Subject.getText().toString();
                message = Message.getText().toString();

                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.putExtra(Intent.EXTRA_EMAIL, new String[]{sendTo});
                Email.putExtra(Intent.EXTRA_SUBJECT, new String[]{subject});
                Email.putExtra(Intent.EXTRA_TEXT, new String[]{message});
                Email.setType("message/rfc822");
                startActivity(Intent.createChooser(Email, "Please choose from below options"));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
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
