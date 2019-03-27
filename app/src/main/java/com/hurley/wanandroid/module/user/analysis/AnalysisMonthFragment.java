package com.hurley.wanandroid.module.user.analysis;

import android.graphics.Color;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseFragment;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.ArticleNumBean;
import com.hurley.wanandroid.bean.RoseBean;
import com.openxu.cview.chart.barchart.BarVerticalChart;
import com.openxu.cview.chart.bean.BarBean;
import com.openxu.cview.chart.rosechart.NightingaleRoseChart;
import com.openxu.utils.DensityUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/24 4:11 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 一月的分析界面
 * </pre>
 */
public class AnalysisMonthFragment extends BaseFragment {

    @BindView(R.id.nrc_analysis_month)
    NightingaleRoseChart mNrcAnalysis;
    @BindView(R.id.bvc_analysis_month)
    BarVerticalChart mBvcAnalysis;

    List<String> systemNames = new ArrayList<>(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getStringSet(Constants.SYSTEM_NAME));
    List<Integer> systemNum = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_analysis_month;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

        List<ArticleNumBean> list = LitePal.findAll(ArticleNumBean.class);

        systemNum.add(list.get(list.size() - 1).getUIMonth());
        systemNum.add(list.get(list.size() - 1).getJNIMonth());
        systemNum.add(list.get(list.size() - 1).getComponentsMonth());
        systemNum.add(list.get(list.size() - 1).getCommCtrlsMonth());
        systemNum.add(list.get(list.size() - 1).getCtrlsMonth());
        systemNum.add(list.get(list.size() - 1).getProjectsMonth());
        systemNum.add(list.get(list.size() - 1).getDataMonth());
        systemNum.add(list.get(list.size() - 1).getHardMonth());
        systemNum.add(list.get(list.size() - 1).getKnowledgeMonth());
        systemNum.add(list.get(list.size() - 1).getImageMonth());
        systemNum.add(list.get(list.size() - 1).getPlatformsMonth());
        systemNum.add(list.get(list.size() - 1).getKotlinMonth());
        systemNum.add(list.get(list.size() - 1).getJetpackMonth());
        systemNum.add(list.get(list.size() - 1).getAnimMonth());
        systemNum.add(list.get(list.size() - 1).getFrameworkMonth());
        systemNum.add(list.get(list.size() - 1).getJavaMonth());
        systemNum.add(list.get(list.size() - 1).getMediaMonth());
        systemNum.add(list.get(list.size() - 1).getNetMonth());
        systemNum.add(list.get(list.size() - 1).getDevMonth());

        //去除掉不需要的文章体系
        filterSuperChapter(systemNames);
        LogUtils.e(systemNames);

        initRoseChart();
        initBarChart();
    }

    private void initRoseChart() {
        List<Object> roseList = new ArrayList<>();
        //是否在图表上显示指示lable
        mNrcAnalysis.setShowChartLable(true);
        //是否在图表上显示指示num
        mNrcAnalysis.setShowChartNum(false);
        //点击显示数量
        mNrcAnalysis.setShowNumTouched(false);
        //右侧显示数量
        mNrcAnalysis.setShowRightNum(true);

        for (int i = 0; i < systemNames.size(); i ++) {
            roseList.add(new RoseBean(systemNum.get(i), systemNames.get(i)));
        }

        //参数1：数据对象class， 参数2：数量属性字段名称， 参数3：名称属性字段名称， 参数4：数据集合
        mNrcAnalysis.setData(RoseBean.class, "count", "ClassName", roseList);
        //是否正在加载，数据加载完毕后置为false
        mNrcAnalysis.setLoading(false);
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
