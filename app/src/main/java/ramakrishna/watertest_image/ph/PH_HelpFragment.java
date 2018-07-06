package ramakrishna.watertest_image.ph;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.LongOperation;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class PH_HelpFragment extends Fragment {


    WebView mWebView;
    private List<Result> notesList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_ph__description, null);
        mWebView = (WebView) v.findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("file:///android_asset/phtest.html");
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
}
