package com.hurley.codehub.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.gyf.barlibrary.ImmersionBar;
import com.hjq.language.MultiLanguages;
import com.hurley.codehub.R;
import com.hurley.codehub.app.App;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.app.LoadType;
import com.hurley.codehub.di.component.ActivityComponent;
import com.hurley.codehub.di.component.DaggerActivityComponent;
import com.hurley.codehub.di.module.ActivityModule;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19
 *      github : https://github.com/HurleyJames
 *      desc   : Activity基类
 * </pre>
 */
public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends RxAppCompatActivity implements ISupportActivity, BaseContract.BaseView {
    @Nullable
    @Inject
    protected T mPresenter;
    protected ActivityComponent mActivityComponent;
    @Nullable
    protected Toolbar mToolbar;
    private Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initView();

    /**
     * 是否显示返回键
     *
     * @return
     */
    protected boolean showHomeAsUp() {
        return false;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        initActivityComponent();
        ARouter.getInstance().inject(this);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        unbinder = ButterKnife.bind(this);
        initToolBar();
        attachView();
        initView();
        if (!NetworkUtils.isConnected()) {
            showNoNet();
        }
        ImmersionBar.with(this)
                // 与导航栏同色
                .statusBarColor(R.color.colorPrimary)
                // 解决状态栏和布局重叠问题
                .fitsSystemWindows(true)
                // 初始化
                .init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
        unbinder.unbind();
        detachView();
        //必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String successMsg) {
        ToastUtils.showShort(successMsg);
    }

    @Override
    public void showFailed(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void showNoNet() {
        ToastUtils.showShort(R.string.http_error);
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
        return true;
    }

    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }


    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    @Override
    final public void onBackPressed() {
        mDelegate.onBackPressed();
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国家化适配（绑定语种）
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }

    protected void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * 设置加载数据结果
     *
     * @param baseQuickAdapter
     * @param refreshLayout
     * @param list
     * @param loadType
     */
    protected void setLoadDataResult(BaseQuickAdapter baseQuickAdapter, SwipeRefreshLayout refreshLayout, List list, @LoadType.checker int loadType) {
        switch (loadType) {
            case LoadType.TYPE_REFRESH_SUCCESS:
                baseQuickAdapter.setNewData(list);
                refreshLayout.setRefreshing(false);
                break;
            case LoadType.TYPE_REFRESH_ERROR:
                refreshLayout.setRefreshing(false);
                break;
            case LoadType.TYPE_LOAD_MORE_SUCCESS:
                if (list != null) {
                    baseQuickAdapter.addData(list);
                }
                break;
            case LoadType.TYPE_LOAD_MORE_ERROR:
                baseQuickAdapter.loadMoreFail();
                break;
            default:
                break;
        }
        if (list == null || list.isEmpty() || list.size() < Constants.PAGE_SIZE) {
            baseQuickAdapter.loadMoreEnd(false);
        } else {
            baseQuickAdapter.loadMoreComplete();
        }
    }

    //选择性拓展其他方法

    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }


    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * 加载多个同级根Fragment,类似WeChat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment);
    }

    /**
     * 初始化ActivityComponent
     */
    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException("toolbar can not be null");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());
        //toolbar除掉阴影
        getSupportActionBar().setElevation(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(0);
        }
    }

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 显示Toast
     *
     * @param text
     */
    protected void toast(CharSequence text) {
        //设置Toast背景颜色
        ToastUtils.setBgColor(ContextCompat.getColor(this, R.color.gray));
        ToastUtils.showShort(text);
    }

    /**
     * 显示Toast
     *
     * @param id
     */
    protected void toast(int id) {
        //设置Toast背景颜色
        ToastUtils.setBgColor(ContextCompat.getColor(this, R.color.gray));
        ToastUtils.showShort(id);
    }

}














