package com.inideveloper.share.cameraandroid.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.inideveloper.share.cameraandroid.app.util.CommonUtils;

import java.io.File;


public class MainActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 81;
    private ImageView ivResult;
    private String pathFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pathFile = "";
        ivResult = (ImageView)findViewById(R.id.iv_result);

        ImageButton ibCamera = (ImageButton)findViewById(R.id.ib_camera);
        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+
                        File.separator + "tmp_" + String.valueOf(System.currentTimeMillis()) +
                        ".jpg";
                File file = new File(pathFile);

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        Button btnNext = (Button)findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pathFile.equals("")) {
                    Intent intent = new Intent(MainActivity.this, NextActivity.class);
                    intent.putExtra(NextActivity.EXTRA_IMAGE_NAME, pathFile);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE){
            if (resultCode == RESULT_OK){

                Bitmap bitmap = CommonUtils.decodeSampledBitmapFromFile(pathFile, 1000, 700);

                ivResult.setImageBitmap(bitmap);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
