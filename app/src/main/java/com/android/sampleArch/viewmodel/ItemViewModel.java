package com.android.sampleArch.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.sampleArch.Utils.Constants;
import com.android.sampleArch.contract.IOkHttp;
import com.android.sampleArch.model.OkHttpModel;

import java.util.BitSet;

public class ItemViewModel extends ViewModel implements IOkHttp{
    MutableLiveData<Bitmap> bitmapMutableLiveData;
    OkHttpModel okHttpModel;

    public ItemViewModel(){
        bitmapMutableLiveData = new MutableLiveData<Bitmap>();
        okHttpModel = new OkHttpModel(this);
    }

    public void getImage(String url){
        okHttpModel.getImage(url);
    }

    public MutableLiveData<Bitmap> getBitmapMutableLiveData() {
        return bitmapMutableLiveData;
    }

    @Override
    public void onResponse(Bitmap bitmap) {
        Log.d(Constants.TAG,"onResponse");
        bitmapMutableLiveData.postValue(bitmap);
//        bitmapMutableLiveData.setValue(bitmap);
    }
}
