package com.hurley.wanandroid.module.user.analysis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.PieBean;
import com.openxu.cview.chart.barchart.BarVerticalChart;
import com.openxu.cview.chart.bean.BarBean;
import com.openxu.cview.chart.piechart.PieChartLayout;
import com.openxu.utils.DensityUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/24 3:18 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 一周的分析界面
 * </pre>
 */
public class AnalysisWeekFragment extends BaseFragment<AnalysisWeekPresenter> implements AnalysisWeekContract.View {

    @BindView(R.id.pcl_analysis_week)
    PieChartLayout mPclAnalysis;
    @BindView(R.id.bvc_analysis_week)
    BarVerticalChart mBvcAnalysis;

    List<String> systemNames = new ArrayList<>(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getStringSet(Constants.SYSTEM_NAME));
    List<Integer> systemNum = new ArrayList<>();

    public static AnalysisWeekFragment newInstance() {
        Bundle args = new Bundle();
        AnalysisWeekFragment fragment = new AnalysisWeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_analysis_week;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        //mPresenter.getAllArticlesInWeek();

        //从首选项中取到数据，并添加到集合中
        int ui = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.UI);
        int JNI = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.JNI);
        int components = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.COMPONENTS);
        int commCtrls = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.COMM_CTRLS);
        int ctrls = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.CTRLS);
        int projects = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.PROJECTS);
        int data = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.DATA);
        int hardware = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.HARDWARE);
        int knowledge = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.KNOWLEDAGE);
        int image = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.IMAGE);
        int platforms = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.PLATFORMS);
        int Kotlin = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.KOTLIN);
        int Jetpack = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.JETPACK);
        int anim = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.ANIM);
        int framework = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.FRAMEWORK);
        int Java = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.JAVA);
        int media = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.MEDIA);
        int net = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.NET);
        int dev = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.DEV);

        systemNum.add(ui);
        systemNum.add(JNI);
        systemNum.add(components);
        systemNum.add(commCtrls);
        systemNum.add(ctrls);
        systemNum.add(projects);
        systemNum.add(data);
        systemNum.add(hardware);
        systemNum.add(knowledge);
        systemNum.add(image);
        systemNum.add(platforms);
        systemNum.add(Kotlin);
        systemNum.add(Jetpack);
        systemNum.add(anim);
        systemNum.add(framework);
        systemNum.add(Java);
        systemNum.add(media);
        systemNum.add(net);
        systemNum.add(dev);
        LogUtils.e(systemNum);

        //去除掉不需要的文章体系
        filterSuperChapter(systemNames);
        LogUtils.e(systemNames);

        initPieChart();
        initBarChart();
    }

    private void initPieChart() {

        //请求数据
        List<PieBean> datalist = new ArrayList<>();
        for (int i = 0; i < systemNames.size(); i ++) {
            datalist.add(new PieBean(systemNum.get(i), systemNames.get(i)));
        }

        //圆环宽度，如果值>0,则为空心圆环，内环为白色，可以在内环中绘制字
        mPclAnalysis.setRingWidth(DensityUtil.dip2px(Objects.requireNonNull(getContext()), 0));
        mPclAnalysis.setTagModul(PieChartLayout.TAG_MODUL.MODUL_LABLE);
        mPclAnalysis.setDebug(false);
        mPclAnalysis.setLoading(false);
        mPclAnalysis.setChartData(PieBean.class, "Numner", "Name", datalist ,null);
    }

    private void initBarChart() {
        //Y轴数据
        List<List<BarBean>> YList = new ArrayList<>();
        //X轴数据
        List<String> XList = new ArrayList<>();

        //双柱间距
        mBvcAnalysis.setBarSpace(DensityUtil.dip2px(getContext(), 1));
        //柱间距
        mBvcAnalysis.setBarItemSpace(DensityUtil.dip2px(getContext(), 15));
        mBvcAnalysis.setShowEnd(false);
        mBvcAnalysis.setDebug(false);
        mBvcAnalysis.setBarColor(new int[]{Color.parseColor("#03A9F4")});

        for(int i = 0; i < systemNames.size(); i ++){
            //此集合为柱状图上一条数据，集合中包含几个实体就是几个柱子
            List<BarBean> list = new ArrayList<>();
            list.add(new BarBean(systemNum.get(i), ""));
            YList.add(list);
            XList.add(systemNames.get(i));
        }

        mBvcAnalysis.setLoading(false);
        mBvcAnalysis.setData(YList, XList);
    }

    /**
     * 过滤部分体系
     * @param list
     */
    private void filterSuperChapter(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (!str.equals(getString(R.string.dev_environment)) &&
                    !str.equals(getString(R.string.basic_knowledge)) &&
                    !str.equals(getString(R.string.android_components)) &&
                    !str.equals(getString(R.string.common_ctrl)) &&
                    !str.equals(getString(R.string.UI)) &&
                    !str.equals(getString(R.string.network_access)) &&
                    !str.equals(getString(R.string.image_loading)) &&
                    !str.equals(getString(R.string.data_storage)) &&
                    !str.equals(getString(R.string.animation_effect)) &&
                    !str.equals(getString(R.string.customize_ctrl)) &&
                    !str.equals(getString(R.string.media_tech)) &&
                    !str.equals(getString(R.string.hardware_module)) &&
                    !str.equals(getString(R.string.JNI)) &&
                    !str.equals(getString(R.string.framework)) &&
                    !str.equals(getString(R.string.Kotlin)) &&
                    !str.equals(getString(R.string.advanced_Java)) &&
                    !str.equals(getString(R.string.cross_platform)) &&
                    !str.equals(getString(R.string.project_manage)) &&
                    !str.equals(getString(R.string.Jetpack))) {
                //从list中删除
                iterator.remove();
            }
        }
    }
}
