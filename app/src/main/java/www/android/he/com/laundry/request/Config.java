package www.android.he.com.laundry.request;

import android.content.Intent;

/**
 * 网络请求时设置的显示参数
 * <p/>
 * 比如：控制toast/loading的显示或隐藏
 * <p/>
 * 如果还有其他需要设置的参数（用于控制界面显示），在Config类中添加。
 */
public class Config {

    /**
     * @param showLoading -是否显示loading指示
     * @param showToast   -请求失败后是否显示toast
     */
    public Config(boolean showLoading, boolean showToast) {
        this.showLoading = showLoading;
        this.showToast = showToast;
    }

    public Config() {
    }

    private boolean showToast = true; // 默认弹出toast
    private boolean showLoading = true; // 默认显示loading指示
    private boolean skipDirectly = false; // token失效，默认不直接跳转
    private boolean isFullScreen = false; // 显示loading指示时，isFullScreen=true，表示title部分也不可以点击；
    private Intent intent; // 用于添加传递更多参数

    public Intent getIntent() {
        return intent;
    }

    public Config setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public boolean isShowToast() {
        return showToast;
    }

    public Config setShowToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public boolean isShowLoading() {
        return showLoading;
    }

    public Config setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        return this;
    }

    public boolean isSkipDirectly() {
        return skipDirectly;
    }

    public Config setIsFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        return this;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }


}
