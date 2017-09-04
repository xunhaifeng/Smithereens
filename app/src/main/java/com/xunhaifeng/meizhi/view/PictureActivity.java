/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xunhaifeng.meizhi.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xunhaifeng.R;
import com.xunhaifeng.meizhi.util.RxMeizhi;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";


    ImageView mImageView;
    PhotoViewAttacher mPhotoViewAttacher;
    String mImageUrl, mImageTitle;

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }


    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        mImageView = (ImageView) findViewById(R.id.picture);
        parseIntent();
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mImageView);
        setTitle(mImageTitle);
        setupPhotoAttacher();
    }


    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        // @formatter:off
        mPhotoViewAttacher.setOnLongClickListener(v -> {
            new AlertDialog.Builder(PictureActivity.this)
                    .setMessage(getString(R.string.ask_saving_picture))
                    .setNegativeButton(android.R.string.cancel,
                            (dialog, which) -> dialog.dismiss())
                    .setPositiveButton(android.R.string.ok,
                            (dialog, which) -> {
                                saveImageToGallery();
                                dialog.dismiss();
                            })
                    .show();
            return true;
        });
    }


    private void saveImageToGallery() {
        // @formatter:off
       RxMeizhi.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(uri -> {
                   File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
                   String msg = String.format(getString(R.string.picture_has_save_to),
                           appDir.getAbsolutePath());
                   Toast.makeText(PictureActivity.this,msg,Toast.LENGTH_LONG).show();
               });
        // @formatter:on

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPhotoViewAttacher!= null){
            mPhotoViewAttacher.cleanup();
        }
    }
    @Override public boolean canBack() {
        return true;
    }
}
