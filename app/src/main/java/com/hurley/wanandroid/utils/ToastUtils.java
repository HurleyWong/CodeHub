package com.hurley.wanandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 上午11:18
 *      github : https://github.com/HurleyJames
 *      desc   : Toast工具类
 * </pre>
 */
public class ToastUtils {
    private static final String TAG = "ToastUtils";

    private static Toast sToast;

    private static final int     COLOR_DEFAULT = 0xFEFFFFF;
    private static final Handler mHandler      = new Handler(Looper.getMainLooper());
    private static final String  NULL          = "null";

    private static int sGravity     = -1;
    private static int sXOffset     = -1;
    private static int sYOffset     = -1;
    private static int sBgColor     = COLOR_DEFAULT;
    private static int sBgResouce   = -1;
    private static int sMsgColor    = COLOR_DEFAULT;
    private static int sMsgTextSize = -1;

    private static Toast getToast(Context context) {
        if (sToast != null) {
            return sToast;
        }
        if (context == null) {
            return null;
        }
        sToast = new Toast(context.getApplicationContext());
        return sToast;
    }

    /**
     * 短时长Toast
     * @param context
     * @param resId
     */
    public static void showShortToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时长Toast
     * @param context
     * @param msg
     */
    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时长Toast
     * @param context
     * @param resId
     */
    public static void showLongToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 长时长Toast
     * @param context
     * @param msg
     */
    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示自定义View时长的Toast
     * @param context
     * @param view
     * @param duration
     */
    @SuppressLint("NewApi")
    public static void showToast(Context context, View view, int duration) {
        try {
            Toast toast = getToast(context);
            if (toast == null) {
                return;
            }
            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示自定义布局时长的Toast
     * @param context
     * @param text
     * @param layoutId
     * @param textId
     * @param duration
     */
    public static void showToast(final Context context,
                                 final String text,
                                 final int layoutId,
                                 final int textId,
                                 final int duration) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                View layout = getView(layoutId);
                TextView toastText = layout.findViewById(textId);
                toastText.setText(text);
                //实例化一个Toast对象
                Toast toast = new Toast(context);
                //设置显示时长
                toast.setDuration(duration);
                toast.setView(layout);
                toast.show();
            }
        });
    }

    /**
     * 关闭Toast
     * @param context
     */
    public static void cancelToast(Context context) {
        Toast toast = getToast(context);
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 获取Toast布局文件
     * @param layoutId
     * @return
     */
    private static View getView(@LayoutRes final int layoutId) {
        LayoutInflater inflater = (LayoutInflater) Utils.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, null);
    }

    /**
     * 设置Toast位置
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void setGravity(final int gravity, final int xOffset, final int yOffset) {
        sGravity = gravity;
        sXOffset = xOffset;
        sYOffset = yOffset;
    }

    /**
     * 设置Toast背景颜色
     * @param backgroundColor
     */
    public static void setBgColor(@ColorInt final int backgroundColor) {
        sBgColor = backgroundColor;
    }

    /**
     * 设置Toast背景资源
     * @param bgResource
     */
    public static void setBgResource(@DrawableRes final int bgResource) {
        sBgResouce = bgResource;
    }

    /**
     * 设置Toast消息颜色
     * @param msgColor
     */
    public static void setMsgColor(@ColorInt final int msgColor) {
        sMsgColor = msgColor;
    }

    /**
     * 设置Toast字体大小
     * @param textSize
     */
    public static void setMsgTextSize(final int textSize) {
        sMsgTextSize = textSize;
    }

}
