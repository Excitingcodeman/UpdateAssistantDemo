package com.gs.updateassistant;

import android.os.SystemClock;
import android.view.View;

/**
 * create on 2018-09-08   13:16
 * 避免重复点击
 *
 * @author husky
 */
public abstract class OnSingleClickListener implements View.OnClickListener {
    /**
     * 最短click事件的时间间隔
     */
    private long MIN_CLICK_INTERVAL = 500;
    /**
     * 上次click的时间
     */
    private long mLastClickTime;

    /**
     * click响应函数
     *
     * @param v The view that was clicked.
     */
    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
        long currentClickTime = SystemClock.uptimeMillis();
        long elapsedTime = currentClickTime - mLastClickTime;
        /** 有可能2次连击，也有可能3连击，保证mLastClickTime记录的总是上次click的时间 */
        mLastClickTime = currentClickTime;

        if (elapsedTime <= MIN_CLICK_INTERVAL) {
            return;
        }

        onSingleClick(v);
    }

    public OnSingleClickListener() {

    }

    public OnSingleClickListener(long interval) {
        MIN_CLICK_INTERVAL = interval;
    }
}
