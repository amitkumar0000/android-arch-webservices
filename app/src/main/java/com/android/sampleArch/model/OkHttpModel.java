package com.android.sampleArch.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;

import com.android.sampleArch.Utils.Constants;
import com.android.sampleArch.contract.IOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpModel {
    OkHttpClient okHttpClient;
    IOkHttp iOkHttp;

    public OkHttpModel(IOkHttp iOkHttp){
        this.iOkHttp = iOkHttp;
        okHttpClient = new OkHttpClient();
    }

    public void getImage(String imageURL){
       new ImageAsync().execute(imageURL);
    }

    class ImageAsync extends AsyncTask<String, Void, Void> {

        public ImageAsync(){

        }

        @Override
        protected Void doInBackground(String... param) {
            String imageURL = param[0];
            Request request = new Request.Builder().url(imageURL).build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    byte[] imageByte = response.body().bytes();

                    BitmapFactory.Options options = new BitmapFactory.Options();

                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length,options);
                    Log.d(Constants.TAG,"Image onResponse");

                    iOkHttp.onResponse(bitmap);

                }
            });
            return null;
        }
    }

}
