package com.example.zx.myapplication.imgloader.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class MemoryCacheHelper {

    private static final String TAG = "MemoryCacheHelper";

    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheHelper(Context mContext){
        this.mContext = mContext;
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024); //获得应用最大内存
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public LruCache<String, Bitmap> getmMemoryCache() {
        return mMemoryCache;
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        Log.v(TAG, "加载内存缓存中的图片");
        return mMemoryCache.get(key);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(getBitmapFromMemoryCache(key) == null) {
            Log.v(TAG, "addBitmapToMemoryCache");
            mMemoryCache.put(key, bitmap);
        }
    }
}
