package com.hurley.codehub.module.user.analysis;

import android.graphics.Color;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.utils.FakeElementLoadingView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 5:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 加载界面
 * </pre>
 */
@Route(path = PathContainer.LOADING)
public class LoadingDataActivity extends BaseActivity<LoadingDataPresenter> implements LoadingDataContract.View {

    @BindView(R.id.loadingView)
    FakeElementLoadingView mLoadingView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        assert mPresenter != null;
        mPresenter.getAllArticles();


        mLoadingView.addBitmap(R.mipmap.ic_git);
        mLoadingView.addBitmap(R.mipmap.ic_slack);
        mLoadingView.addBitmap(R.mipmap.ic_spotify);
        mLoadingView.addBitmap(R.mipmap.ic_docker);
        mLoadingView.setShadowColor(Color.LTGRAY);
        mLoadingView.setDuration(700);
        mLoadingView.start();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ARouter.getInstance().build(PathContainer.ANALYSIS).navigation();
                finish();
            }
        };

        //3秒后执行跳转
        timer.schedule(task, 1000 * 3);

    }

    /**
     * 显示返回键
     *
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
