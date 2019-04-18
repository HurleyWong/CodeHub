package com.hurley.codehub.module.wanandroid.core.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hurley.codehub.R;
import com.hurley.codehub.bean.wanandroid.SystemBean;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午8:35
 *      github : https://github.com/HurleyJames
 *      desc   : 体系适配器类
 * </pre>
 */
public class SystemAdapter extends BaseQuickAdapter<SystemBean, BaseViewHolder> {

    @Inject
    public SystemAdapter() {
        super(R.layout.item_system, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemBean item) {
        helper.setText(R.id.tv_system_title, item.getName());
        StringBuffer stringBuffer = new StringBuffer();
        for (SystemBean.Children systemBean : item.getChildren()) {
            //设置两者之间的间隔
            stringBuffer.append(systemBean.getName() + "     ");
        }
        helper.setText(R.id.tv_system_subtitle, stringBuffer.toString());
    }
}
