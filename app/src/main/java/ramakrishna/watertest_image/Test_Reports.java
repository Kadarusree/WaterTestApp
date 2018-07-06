package ramakrishna.watertest_image;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.NotesAdapter;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;
import ramakrishna.watertest_image.utils.MyDividerItemDecoration;
import ramakrishna.watertest_image.utils.RecyclerTouchListener;

public class Test_Reports extends AppCompatActivity {

    TextView tv_ph_value, tv_fl_value, tv_tds_value;
    TextView tv_ph_range, tv_fl_range, tv_tds_range;

    TextView tv_na_value, tv_ni_value;
    TextView tv_na_range, tv_ni_range;

    TextView ch_value, ch_range;

    MediaPlayer mPlayer;
    ////////new test Reports///////
    private NotesAdapter mAdapter;
    private List<Result> notesList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TextView noNotesView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__reports);

        DatabaseHelper db = new DatabaseHelper(this);
        mPlayer= MediaPlayer.create(getApplicationContext(), R.raw.mail);
        mPlayer.start();
        notesList.addAll(db.getmailNotes());
        noNotesView=(TextView)findViewById(R.id.empty_notes_view);

        if (db.getmailNotes().size()==0){
            noNotesView.setText("No Results Found");
        }

        String mHtmlString  = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "    text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Table Caption</h2>\n" +
                "<p>To add a caption to a table, use the caption tag.</p>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <caption>Monthly savings</caption>\n" +
                "  <tr>\n" +
                "    <th>Month</th>\n" +
                "    <th>Savings</th>\n" +
                "  </tr>";

        for (int i=0;i<notesList.size();i++){
            Result mRes = notesList.get(i);
         mHtmlString =  mHtmlString +  "<tr>"+"<td>"+mRes.getTestName()+"</td>"+"<td>"+mRes.getTestResult()+"</td>"+"<td>"+mRes.getColor()+"</td>"+"</tr>";

        }

        mHtmlString = mHtmlString+"</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        tv_ph_value=(TextView)findViewById(R.id.tv_ph_value);
        tv_fl_value=(TextView)findViewById(R.id.tv_fl_value);
        tv_tds_value=(TextView)findViewById(R.id.tv_tds_value);

        tv_ph_range=(TextView)findViewById(R.id.tv_ph_range);
        tv_fl_range=(TextView)findViewById(R.id.tv_fl_range);
        tv_tds_range=(TextView)findViewById(R.id.tv_tds_range);

        tv_na_value=(TextView)findViewById(R.id.tv_na_value);
        tv_ni_value=(TextView)findViewById(R.id.tv_ni_value);


        tv_na_range=(TextView)findViewById(R.id.tv_na_range);
        tv_ni_range=(TextView)findViewById(R.id.tv_ni_range);

        ch_value=(TextView)findViewById(R.id.tv_ch_value) ;
        ch_range=(TextView)findViewById(R.id.tv_ch_range) ;



        read();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);



        mAdapter = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this));
        recyclerView.setAdapter(mAdapter);





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                } else {

                }
            }
        });
        builder.show();
    }

    public void read(){
        SharedPreferences mSharedPreferences=getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        tv_ph_value.setText(mSharedPreferences.getString("ph_value","-"));
        tv_fl_value.setText(mSharedPreferences.getString("fl_value","-")+"mg/L");
        tv_tds_value.setText(mSharedPreferences.getString("tds_value","-"));
        tv_na_value.setText(mSharedPreferences.getString("na_value","-")+"mg/L");
        tv_ni_value.setText(mSharedPreferences.getString("ni_value","-")+"mg/L");
        ch_value.setText(mSharedPreferences.getString("ch_value","-"));



        tv_ph_range.setText(mSharedPreferences.getString("ph_range","-"));
        tv_fl_range.setText(mSharedPreferences.getString("fl_range","-"));
        tv_tds_range.setText(mSharedPreferences.getString("tds_range","-"));
        tv_na_range.setText(mSharedPreferences.getString("na_range","-"));
        tv_ni_range.setText(mSharedPreferences.getString("ni_range","-"));
        ch_range.setText(mSharedPreferences.getString("ch_range","-"));


        tv_fl_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("fl_color_code","#ffffff")));
        tv_tds_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("tds_color_code","#ffffff")));
        tv_ph_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("ph_color_code","#ffffff")));
        tv_na_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("na_color_code","#ffffff")));
        tv_ni_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("ni_color_code","#ffffff")));
        ch_range.setBackgroundColor(Color.parseColor(mSharedPreferences.getString("ch_color_code","#ffffff")));

    }


    @Override
    protected void onStop() {
        super.onStop();
        mPlayer.release();
    }
}
