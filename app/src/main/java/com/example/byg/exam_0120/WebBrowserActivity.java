package com.example.byg.exam_0120;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WebBrowserActivity extends AppCompatActivity {

    private EditText mUrlEditText;
    private WebView mWebView;

    private Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        mUrlEditText = (EditText) findViewById(R.id.url_edit);
        mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 일반적
        mWebView.setWebViewClient(new WebViewClient());
        // 기능 확장
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Toast.makeText(WebBrowserActivity.this, "로딩 완료", Toast.LENGTH_SHORT).show();
//            }
//        });

        mSearchButton = (Button) findViewById(R.id.search_btn);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mUrlEditText.getText().toString();
                if (!url.contains("http://")) {
                    url = "http://" + url;
                }
                mWebView.loadUrl(url);
            }

        });
        // 키보드 이벤트 검출
        mUrlEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    //mWebView.loadUrl(mUrlEditText.getText().toString());
                    // 버튼을 코드로 누르기
                    mSearchButton.callOnClick();

                    // 키보드 숨기기
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mUrlEditText.getWindowToken(), 0);
                }
                return true;
            }
        });
    }

    // 뒤로가기 키 캐치
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_setting:
                if (mWebView.canGoBack()) {
                    item.setEnabled(true);
                    mWebView.goBack();
                } else {
                    item.setEnabled(false);
                }
                return true;
            case R.id.forward_setting:
                if (mWebView.canGoForward()) {
                    item.setEnabled(true);
                    mWebView.goForward();
                } else {
                    item.setEnabled(false);
                }
                return true;
            case R.id.refresh_setting:
                mWebView.reload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
