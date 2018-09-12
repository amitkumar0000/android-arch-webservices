package com.android.sampleArch.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.android.sampleArch.R;
import com.android.sampleArch.Utils.Constants;
import com.android.sampleArch.contract.IIpresenter;
import com.android.sampleArch.contract.IViewInterface;
import com.android.sampleArch.presenter.IPresenter;
import com.android.sampleArch.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IViewInterface{

    Button button;
    ImageView imageView;

    IIpresenter iPresenter;
    ItemViewModel itemViewModel;
    CheckBox mvp,mvvm;
    RadioButton mvpRadio, mvvmRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(Constants.TAG,"onCreate");

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(this);
        itemViewModel  = ViewModelProviders.of(this).get(ItemViewModel.class);
        iPresenter = new IPresenter(this);

        mvp = findViewById(R.id.MVP);
        mvvm = findViewById(R.id.MVVM);

        mvpRadio = findViewById(R.id.RadioMVP);
        mvvmRadio = findViewById(R.id.RadioMVVM);

        subScribeItem();
    }

    private void subScribeItem() {

        itemViewModel.getBitmapMutableLiveData().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable Bitmap bitmap) {
                Log.d(Constants.TAG,"OnChanged");
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG,"onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG,"onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG,"onDestroy");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                if(mvpRadio.isChecked()) {
                    Log.d(Constants.TAG,"USing MVP ARCH");
                    iPresenter.getImage(Constants.url);
                }else if(mvvmRadio.isChecked()){
                    Log.d(Constants.TAG,"USing MVVM ARCH");
                    itemViewModel.getImage(Constants.url);
                }
                break;

            }
        }
    }

    @Override
    public void onGetImageResponse(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(Constants.TAG,"isFinishing");
                imageView.setImageBitmap(bitmap);

                if(!isFinishing()) {
                    Log.d(Constants.TAG,"!isFinishing");

                }
            }
        });
    }
}
