package com.android.sampleArch.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.android.sampleArch.Utils.Constants;
import com.android.sampleArch.contract.IIpresenter;
import com.android.sampleArch.contract.IOkHttp;
import com.android.sampleArch.contract.IViewInterface;
import com.android.sampleArch.model.OkHttpModel;

public class IPresenter implements IIpresenter,IOkHttp {

    IViewInterface iViewInterface;
    OkHttpModel okHttpModel;

    public IPresenter(IViewInterface viewInterface) {
        iViewInterface = viewInterface;
        okHttpModel = new OkHttpModel(this);
    }

    public void getImage(String url) {
        okHttpModel.getImage(Constants.url);
    }

    @Override
    public void onResponse(Bitmap bitmap) {
        Log.d(Constants.TAG,"onResponse");
        iViewInterface.onGetImageResponse(bitmap);
    }
}
