package ramakrishna.watertest_image.ph;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;
import ramakrishna.watertest_image.utils.LongOperation;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class PH_HelpFragment extends Fragment {


    WebView mWebView;
    private List<Result> notesList = new ArrayList<>();

    private Locale mBackedUpLocale = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_ph__description, null);


        // Toast.makeText(getActivity(),mBackedUpLocale.getLanguage()+"",Toast.LENGTH_SHORT).show();
        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);


        mBackedUpLocale = Constants.locale;




        if (mBackedUpLocale.getLanguage().equalsIgnoreCase("te")){
            mWebView.loadUrl("file:///android_asset/ph_test.htm");
        }
        else{
            mWebView.loadUrl("file:///android_asset/phtest.html");

        }

        DatabaseHelper db = new DatabaseHelper(getActivity());

        notesList.addAll(db.getAllNotes());

        String mHtmlString  = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    font-family: arial, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    width: 40%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "    border: 1px solid #dddddd;\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\n <h2>Water Test Results</h2>"+
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Test Name</th>\n" +
                "    <th>Result Value</th>\n" +
                "    <th>Time</th>\n" +
                "  </tr>";

        for (int i=0;i<notesList.size();i++){
            Result mRes = notesList.get(i);
            mHtmlString =  mHtmlString +  "<tr>"+"<td>"+mRes.getTestName()+"</td>"+"<td>"+mRes.getTestResult()+"</td>"+"<td>"+mRes.getTimeStamp()+"</td>"+"</tr>";

        }

        mHtmlString = mHtmlString+"</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

   //     mWebView.loadData(mHtmlString, "text/html", "UTF-8");

   //     LongOperation mOperation =  new LongOperation("ksreekanthyadav@gmail.com", "Test",mHtmlString);
   //     mOperation.execute();
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
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
}
