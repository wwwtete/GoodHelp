package com.wangw.goodhelp.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.wangw.goodhelp.R;

/**
 * @Description:带进度条的WebView
 * @author http://blog.csdn.net/finddreams
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                5, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        // setWebViewClient(new WebViewClient(){});
        setWebChromeClient(new WebChromeClient());
        //是否可以缩放
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressbar.setProgress(newProgress);
            if (newProgress == 100){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setVisibility(GONE);
                    }
                },500);
            }
//            if (newProgress == 100) {
//
//            } else {
//                if (progressbar.getVisibility() == GONE)
//                    progressbar.setVisibility(VISIBLE);
//                progressbar.setProgress(newProgress);
//            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}