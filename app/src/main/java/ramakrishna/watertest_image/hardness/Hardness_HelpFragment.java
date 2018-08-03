package ramakrishna.watertest_image.hardness;

import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class Hardness_HelpFragment extends Fragment {

    @Nullable
    WebView mWebView;

    private Locale mBackedUpLocale = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_ph__description, null);
        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

         mBackedUpLocale = Constants.locale;

       // mWebView.loadUrl("file:///android_res/raw/hardness.htm");



        if (mBackedUpLocale.getLanguage().equalsIgnoreCase("te")){
            mWebView.loadUrl("file:///android_asset/hardness_telugu.htm");
        }
        else{
            mWebView.loadUrl("file:///android_asset/hardness.htm");

        }
        return v;
    }

    public void fixLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources resources = getResources();
            final Configuration config = resources.getConfiguration();

            if (null != mBackedUpLocale && !config.getLocales().get(0).equals(mBackedUpLocale)) {
                Locale.setDefault(mBackedUpLocale);
                final Configuration newConfig = new Configuration(config);
                newConfig.setLocale(new Locale(mBackedUpLocale.getLanguage(), mBackedUpLocale.getCountry()));
                resources.updateConfiguration(newConfig, null);
            }

            // Also this must be overridden, otherwise for example when opening a dialog the title could have one language and the content other, because
            // different contexts are used to get the resources.
            Resources appResources = getActivity().getResources();
            final Configuration appConfig = appResources.getConfiguration();
            if (null != mBackedUpLocale && !appConfig.getLocales().get(0).equals(mBackedUpLocale)) {
                Locale.setDefault(mBackedUpLocale);
                final Configuration newConfig = new Configuration(appConfig);
                newConfig.setLocale(new Locale(mBackedUpLocale.getLanguage(), mBackedUpLocale.getCountry()));
                appResources.updateConfiguration(newConfig, null);
            }

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        fixLocale();
    }
    @Override
    public void onPause() {
        super.onPause();
        fixLocale();

    }
}
