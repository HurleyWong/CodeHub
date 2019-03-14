package com.hurley.wanandroid.module.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.bean.SystemBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午8:35
 *      github : https://github.com/HurleyJames
 *      desc   : 体系适配器类
 * </pre>
 */
public class SystemAdapter extends BaseQuickAdapter<SystemBean, BaseViewHolder> {

    public SystemAdapter(int layoutResId, @Nullable List<SystemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemBean item) {
        helper.setText(R.id.tv_system_title, item.getName());
        StringBuffer childrenName = new StringBuffer();
        for (SystemBean.Children children : item.getChildren()) {
            childrenName.append(children.getName() + "  ");
        }
        helper.setText(R.id.tv_system_content, childrenName.toString());
    }
}
