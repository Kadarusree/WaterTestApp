package ramakrishna.watertest_image.chloride_new;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ramakrishna.watertest_image.R;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class Chloride_HelpFragment extends Fragment {

    WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_ph__description, null);
        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("file:///android_asset/chloride.htm");

        return v;
    }
}
