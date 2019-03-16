package com.hurley.wanandroid.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/16 3:40 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Banner第三方库的图片加载器
 * </pre>
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }
}
