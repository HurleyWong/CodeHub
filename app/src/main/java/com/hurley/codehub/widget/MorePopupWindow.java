package com.hurley.codehub.widget;

import android.content.Context;
import android.view.View;


import com.hurley.codehub.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/22 下午4:47
 *      github : https://github.com/HurleyJames
 *      desc   : 点击更多选项弹出自定义PopupWindow类
 * </pre>
 */
public class MorePopupWindow extends BasePopupWindow {

    public MorePopupWindow(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.ppw_menu_more);
    }

}
