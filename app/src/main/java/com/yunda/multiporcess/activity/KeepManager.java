package com.yunda.multiporcess.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * 广播注册管理单例类
 */
public class KeepManager {
    private KeepReceiver keepReceiver;
    private WeakReference<Activity> mKeepActivity;
    private static KeepManager instance=null;
    private KeepManager(){}
    public static KeepManager getInstance(){
        if(instance==null){
            synchronized (KeepManager.class){
                if(instance==null){
                    instance=new KeepManager();
                }
            }
        }
        return instance;
    }

    /**
     * 注册
     * @param context
     */
    public void registerKeepReceiver(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        keepReceiver=new KeepReceiver();
        context.registerReceiver(keepReceiver,filter);
    }

    /**
     * 反注册
     */
    public void unRegisterKeepReceiver(Context context){
        if(keepReceiver!=null){
            context.unregisterReceiver(keepReceiver);
        }
    }
    /**
     * finish1个像素的KeepActivity
     */
    public void finishKeep(Context context) {
        if(mKeepActivity!=null){
            Activity activity = mKeepActivity.get();
            if(activity!=null){
                activity.finish();
            }
            mKeepActivity=null;
        }
    }

    /**
     * 启动1个像素的KeepActivity
     * @param context
     */
    public void startKeep(Context context) {
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void setKeepActivity(KeepActivity mKeepActivity) {
        this.mKeepActivity = new WeakReference<Activity>(mKeepActivity);
    }
}
