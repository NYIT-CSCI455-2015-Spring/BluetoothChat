package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.util.Log;
import android.widget.Toast;

public class FeedbackActivity extends Activity implements OnClickListener {

    Button Send, Attachment;//Send Button
    EditText SendTo,Subject,Message;//Edit Text
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int Image;
    String sendto, subject,message, attachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Feedback Page
        Send = (Button) findViewById(R.id.sendto);
        SendTo = (EditText) findViewById(R.id.EditSendTo);
        Subject = (EditText) findViewById(R.id.subjectline);
        Message =  (EditText) findViewById(R.id.messageline);
        Attachment = (Button) findViewById(R.id.attach);
        Send.setOnClickListener(this);
        Attachment.setOnClickListener(this);

    }

    protected void onActivityResult(int request, int response, Intent result) {


        if (request == PICK_FROM_GALLERY && response == RESULT_OK){
            Uri pickedimage = result.getData();
            String[] picturepath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedimage, picturepath, null, null, null);
            cursor.moveToFirst();
            Image = cursor.getColumnIndex(picturepath[0]);
            attachment = cursor.getString(Image);
            Log.e("Attachment Path:", attachment);
            URI = Uri.parse("file://" + attachment);
            cursor.close();

        }

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

    @Override
    public void onClick(View v) {

             if (v == Attachment){
                 viewGallery();
             }
            if (v == Send){
                try{
                    sendto = SendTo.getText().toString();
                    subject = Subject.getText().toString();
                    message = Message.getText().toString();
                    Intent EMAIL = new Intent(Intent.ACTION_SEND);
                    EMAIL.setType("plain/text");
                    EMAIL.putExtra(Intent.EXTRA_EMAIL, new String[]{ sendto});
                    EMAIL.putExtra(Intent.EXTRA_SUBJECT, subject);
                    if (URI != null) {
                        EMAIL.putExtra(Intent.EXTRA_STREAM, URI);
                    }
                    EMAIL.putExtra(Intent.EXTRA_TEXT, message);
                    this.startActivity(Intent.createChooser(EMAIL,
                            " Almost Done, please Choose..."));
                }
            catch (Throwable t){

                Toast.makeText(this,
                        "Your request failed! Please try again: " + t.toString(),
                        Toast.LENGTH_LONG).show();
            }
            }

    }
    private void viewGallery() { //opens the gallery

        Intent attachmentintent = new Intent();
        attachmentintent.setType("image/*");
        attachmentintent.setAction(Intent.ACTION_GET_CONTENT);
        attachmentintent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(attachmentintent, "Please Pick an Image Using:"),
                PICK_FROM_GALLERY);

    }
}
