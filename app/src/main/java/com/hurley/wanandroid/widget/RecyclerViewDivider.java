package com.hurley.wanandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/21 下午10:18
 *      github : https://github.com/HurleyJames
 *      desc   : 绘制Recycler中item的分割间距类
 * </pre>
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration{

    private static final String TAG = "RecyclerViewDivider";

    private Paint paint;
    private Drawable divider;
    /**
     * 分割线高度，默认为1px
     */
    private int mDividerHeight=2;
    /**
     * 列表方向
     * LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
     */
    private int mOrientation1;
    private static final int[] ATTRS=new int[]{ android.R.attr.listDivider };

    /**
     * 默认分割线：高度为2px，颜色为灰色
     * @param context
     * @param orientation      列表方向
     */
    public RecyclerViewDivider(Context context, int orientation){
        if(orientation!=LinearLayoutManager.VERTICAL&&orientation!=LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("请输入正确的参数");
        }
        mOrientation1=orientation;
        final TypedArray a=context.obtainStyledAttributes(ATTRS);
        divider=a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定义分割线
     * @param context
     * @param orientation       列表方向
     * @param drawableId        分割线图片
     */
    public RecyclerViewDivider(Context context,int orientation,int drawableId){
        this(context,orientation);
        divider=ContextCompat.getDrawable(context,drawableId);
        mDividerHeight=divider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     * @param context
     * @param orientation       列表方向
     * @param dividerHeight     分割线图片
     * @param dividerColor      分割线颜色
     */
    public RecyclerViewDivider(Context context,int orientation,int dividerHeight,int dividerColor){
        this(context,orientation);
        mDividerHeight=dividerHeight;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 获取分割线尺寸
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);
        outRect.set(0,0,0,mDividerHeight);
    }

    /**
     * 绘制横向item分割线
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent){
        final int left=parent.getPaddingLeft();
        final int right=parent.getMeasuredWidth()-parent.getPaddingRight();
        final int childSize=parent.getChildCount();
        for(int i=0;i<childSize;i++){
            final View child=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)child.getLayoutParams();
            final int top=child.getBottom()+layoutParams.bottomMargin;
            final int bottom=top+mDividerHeight;
            if(divider!=null){
                divider.setBounds(left,top,right,bottom);
                divider.draw(canvas);
            }
            if(paint!=null){
                canvas.drawRect(left,top,right,bottom,paint);
            }
        }
    }
}
