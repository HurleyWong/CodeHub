package com.hurley.codehub.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hurley.codehub.R;


/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午3:54
 *      github : https://github.com/HurleyJames
 *      desc   : 带清除按钮的EditText
 * </pre>
 */
public class ClearEditText extends AppCompatEditText
        implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearIcon;

    private OnTouchListener mOnTouchListener;
    private OnFocusChangeListener mOnFocusChangeListener;

    public ClearEditText(Context context) {
        super(context);
        initialize(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(final Context context) {

        final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_input_del);

        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        mClearIcon = wrappedDrawable;
        mClearIcon.setBounds(0, 0, mClearIcon.getIntrinsicWidth(), mClearIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        super.addTextChangedListener(this);
        ViewCompat.setBackgroundTintList(this, ContextCompat.getColorStateList(context, R.color.black60));
    }

    private void setClearIconVisible(final boolean visible) {
        if (mClearIcon.isVisible() == visible) {
            return;
        }

        mClearIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mClearIcon : null,
                compoundDrawables[3]);
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(final OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    /**
     * {@link OnFocusChangeListener}
     */

    @Override
    public void onFocusChange(final View view, final boolean hasFocus) {
        if (hasFocus && getText() != null) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    /**
     * {@link OnTouchListener}
     */

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setText("");
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    /**
     * {@link TextWatcher}
     */

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {}

//    @Override
//    protected void drawableStateChanged() {
//        super.drawableStateChanged();
//    }
}