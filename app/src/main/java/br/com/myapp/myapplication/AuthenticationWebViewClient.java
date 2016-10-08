package br.com.myapp.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.StringBufferInputStream;
import java.net.HttpCookie;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Gustavo on 01/10/2016.
 */

public final class AuthenticationWebViewClient extends WebViewClient {

    private static final AuthenticationWebViewClient INSTANCE = new AuthenticationWebViewClient();

    private AuthenticationWebViewClient() {
        if (INSTANCE != null) {
            //lançar exceção
        }
    }

    public static AuthenticationWebViewClient getInstance() {
        return INSTANCE;
    }

    private String glbId = null;

    public String getGlbId() {
        return glbId;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
//        if (url.contains("https://login.globo.com/api/authentication")) {
        if (url.contains("https://login.globo.com/login/438/finish")) {
            glbId = getCookie(url, "GLBID");
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(final WebView view, final WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    private String getCookie(String url, String name) {
        final int VALUE = 1; //foundCookie[key = 0, value = 1]
        String cookie = null;
        String cookieString = CookieManager.getInstance().getCookie(url);
        String[] cookiesList = cookieString.split("[;]");
        for (String entry : cookiesList) {
            if (entry.contains(name)) {
                String[] foundCookie = entry.split("[=]");
                cookie = foundCookie[VALUE];
            }
        }
        return cookie;
    }

}
