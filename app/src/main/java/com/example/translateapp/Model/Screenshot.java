package com.example.translateapp.Model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.MeasureSpec;

public class Screenshot {
    private static Screenshot mInstance;

    private Screenshot() {
    }

    public static Screenshot getInstance() {
        if (mInstance == null) {
            synchronized (Screenshot.class) {
                if (mInstance == null) {
                    mInstance = new Screenshot();
                }
            }
        }
        return mInstance;
    }

    public Bitmap takeScreenshotForView(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(view.getHeight(), MeasureSpec.EXACTLY));
        view.layout((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getMeasuredWidth(), (int) view.getY() + view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public Bitmap takeScreenshotForScreen(Activity activity) {
        return takeScreenshotForView(activity.getWindow().getDecorView().getRootView());
    }
}