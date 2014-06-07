package com.inideveloper.share.cameraandroid.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.inideveloper.share.cameraandroid.app.util.CommonUtils;

/**
 * Created by faren on 6/7/14.
 */
public class NextActivity extends Activity {
    public static final String EXTRA_IMAGE_NAME = "imagename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        ImageView ivNext = (ImageView)findViewById(R.id.iv_next);

        Bundle bundle = getIntent().getExtras();

        if (bundle.containsKey(EXTRA_IMAGE_NAME)){
            Bitmap bitmap = CommonUtils.decodeSampledBitmapFromFile(bundle.getString(EXTRA_IMAGE_NAME)
            ,1000, 700);

            ivNext.setImageBitmap(bitmap);
        }

    }
}
