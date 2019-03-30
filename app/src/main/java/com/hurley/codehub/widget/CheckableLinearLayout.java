package com.hurley.codehub.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/20 下午8:15
 *      github : https://github.com/HurleyJames
 *      desc   : 扩大ChechBox点击范围的LinearLayout
 * </pre>
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable {

    private static final String TAG = "CheckableLinearLayout";

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private boolean mChecked = false;

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnClickListener(v -> toggle());
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked != mChecked) {
            mChecked = checked;
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
