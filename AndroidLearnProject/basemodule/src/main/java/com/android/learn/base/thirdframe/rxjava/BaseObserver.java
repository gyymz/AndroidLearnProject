package com.android.learn.base.thirdframe.rxjava;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.android.learn.base.activity.BaseActivity;
import com.android.learn.base.application.CustomApplication;
import com.android.learn.base.mview.BaseView;
import com.android.learn.base.utils.NetUtils;
import com.android.learn.base.utils.Utils;
import com.android.learn.base.view.CustomProgressDialog;

import java.io.IOException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    protected String errMsg = "";
    private boolean isShowError = true;
    private Context context;
//    public static Dialog prgressDialog;

    protected BaseObserver(boolean isShowDialog) {
        // context在CustomProgressDialog中用到
//        this.context = context;
//        if (isShowDialog) {
//            CustomProgressDialog.show(BaseActivity.context);
//        }
    }

    protected BaseObserver(BaseView view, boolean isShowError) {
        this.isShowError = isShowError;
    }


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
//        CustomProgressDialog.cancel();
        if (!NetUtils.isConnected()) {
            errMsg = "网络连接出错,请检查网络";

        } else if (e instanceof HttpException) {
            errMsg = "服务器访问异常(HttpException)";
        } else if (e instanceof IOException) {
            errMsg = "服务器访问异常(IOException)";
        }

        Utils.showToast(errMsg, true);

    }

    @Override
    public void onComplete() {
//        CustomProgressDialog.cancel();
    }

//    public static void createProgressDialog(Activity context) {
//        if (prgressDialog != null && prgressDialog.isShowing()) return;
//        prgressDialog = CustomProgressDialog.createLoadingDialog(context);
//        if (prgressDialog != null) {
//            prgressDialog.setCancelable(true);//允许返回
//            prgressDialog.show();//显示
//        }
//    }
}
