package ramakrishna.watertest_image;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ramakrishna.watertest_image.alkaline.AlkalineMainActivity;
import ramakrishna.watertest_image.chloride_new.ChlorideMainActivity;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.fluoride.FluorideMainActivity;
import ramakrishna.watertest_image.hardness.HardnessMainActivity;
import ramakrishna.watertest_image.iron.IronMainActivity;
import ramakrishna.watertest_image.nitrate.NITRATE_MainActivity;
import ramakrishna.watertest_image.ph.PH_MainActivity;
import ramakrishna.watertest_image.tds.TDS_MainActivity;
import ramakrishna.watertest_image.utils.BiologyTest;
import ramakrishna.watertest_image.utils.Constants;
import ramakrishna.watertest_image.utils.LongOperation;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    MediaPlayer mediaPlayer;

    private RecyclerView recyclerView;
    private TestsAdapter adapter;
    private ArrayList<String> test_names;
    private ArrayList<Integer> test_images;

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_home);
/*
        String languageToLoad = "te"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio_2);
        if (!Constants.audio_played) {
            mediaPlayer.start();
            Constants.audio_played = true;
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        test_names = new ArrayList<>();
        test_names.add(getResources().getString(R.string.db_opt1));
        test_names.add(getResources().getString(R.string.db_opt2));
        test_names.add(getResources().getString(R.string.db_opt3));
        test_names.add(getResources().getString(R.string.db_opt4));
        test_names.add(getResources().getString(R.string.db_opt5));
        test_names.add(getResources().getString(R.string.db_opt6));
        test_names.add(getResources().getString(R.string.db_opt7));
        test_names.add(getResources().getString(R.string.db_opt8));
        // test_names.add("MICROBIOLOGY TEST");
        test_names.add(getResources().getString(R.string.db_opt9));

        test_images = new ArrayList<>();
        test_images.add(R.drawable.img_phtest);
        test_images.add(R.drawable.img_fluoride);
        test_images.add(R.drawable.img_tds);
        test_images.add(R.drawable.img_nitrate);
        test_images.add(R.drawable.img_chloride);
        test_images.add(R.drawable.total_hardness);
        test_images.add(R.drawable.img_alkaline);
        test_images.add(R.drawable.img_iron);
        // test_images.add(R.drawable.biologytest);
        test_images.add(R.drawable.img_reports);


        TestsAdapter adp = new TestsAdapter(this, test_names, test_images);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adp);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position) {
                            case 0:
                                //     startActivity(new Intent(Home.this, PH_Options.class));

                                startActivity(new Intent(Home.this, PH_MainActivity.class));

                                break;
                            case 1:
                                startActivity(new Intent(Home.this, FluorideMainActivity.class));

                                break;
                            case 2:
                                startActivity(new Intent(Home.this, TDS_MainActivity.class));

                                break;
                            case 3:
                                startActivity(new Intent(Home.this, NITRATE_MainActivity.class));
                                //Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();

                                break;
                            case 4:
                                startActivity(new Intent(Home.this, ChlorideMainActivity.class));

                                break;

                            case 5:
                                startActivity(new Intent(Home.this, HardnessMainActivity.class));
                                break;
                            case 6:
                                startActivity(new Intent(Home.this, AlkalineMainActivity.class));

                                break;
                            case 7:
                                startActivity(new Intent(Home.this, IronMainActivity.class));

                                //Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();

                                break;
                            /*case 8:
                                startActivity(new Intent(Home.this, BiologyTest.class));
                                break;*/
                            case 8:
                                startActivity(new Intent(Home.this, Test_Reports.class));


                                break;
                        }

                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ph) {
            startActivity(new Intent(getApplicationContext(), PH_MainActivity.class));
        } else if (id == R.id.nav_fl) {
            startActivity(new Intent(getApplicationContext(), FluorideMainActivity.class));

        } else if (id == R.id.nav_nitrate) {
            startActivity(new Intent(getApplicationContext(), NITRATE_MainActivity.class));

        } else if (id == R.id.nav_tds) {
            startActivity(new Intent(getApplicationContext(), TDS_MainActivity.class));

        } else if (id == R.id.nav_arsenic) {
            startActivity(new Intent(getApplicationContext(), ChlorideMainActivity.class));

        } else if (id == R.id.nav_send) {
            super.onBackPressed();
        } else if (id == R.id.nav_test_summary) {
            startActivity(new Intent(getApplicationContext(), Test_Reports.class));
        } else if (id == R.id.nav_alkaline) {
            startActivity(new Intent(getApplicationContext(), AlkalineMainActivity.class));
        } else if (id == R.id.nav_iron) {
            startActivity(new Intent(getApplicationContext(), IronMainActivity.class));
        } else if (id == R.id.nav_email) {
            sendEmailBox();
        } else if (id == R.id.nav_send) {
            finish();
        }
     else if (id == R.id.youtube) {
        watchYoutubeVideo(this,"M5LsnxbtwWk");
    }else if (id == R.id.nav_hardness) {
            startActivity(new Intent(getApplicationContext(), HardnessMainActivity.class));
        } else if (id == R.id.nav_test_reset) {
           /* SharedPreferences mSharedPreferences=getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
            SharedPreferences.Editor mEditor=mSharedPreferences.edit();
            mEditor.clear();
            mEditor.commit();*/

            DatabaseHelper mHelper = new DatabaseHelper(this);
            int count = mHelper.clearDB();
            Toast.makeText(getApplicationContext(), count + " Records Deleted", Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.release();

    }

    public void sendEmailBox() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.emailbox);
        final EditText mEditText = (EditText) d.findViewById(R.id.email_edt);
        TextView sendEmail = (TextView) d.findViewById(R.id.btn_sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidEmail(mEditText.getText().toString().trim())) {
                    if (!chekNet()){
                        sendmail(mEditText.getText().toString().trim());
                        d.dismiss();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Internet Not Available",Toast.LENGTH_LONG).show();
                    }

                } else {
                    mEditText.setError("Invalid Email");
                }

            }
        });
        d.show();
    }


    public void sendmail(String s) {

        DatabaseHelper db = new DatabaseHelper(this);
        List<Result> notesList = new ArrayList<>();

        notesList.addAll(db.getmailNotes());

        String mHtmlString = "<!DOCTYPE html>\n" +
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
                "<h3>Please Find my Results Below</h3>\n" +
                "\n" +
                "<table style=\"width:50%\">\n" +
                "  <caption>Test Results</caption>\n" +
                "  <tr>\n" +
                "    <th>Test Name</th>\n" +
                "    <th>Value</th>\n" +
                "    <th>Units</th>\n" +
                "  </tr>";

        for (int i = 0; i < notesList.size(); i++) {
            Result mRes = notesList.get(i);
            mHtmlString = mHtmlString + "<tr>" + "<td>" + mRes.getTestName() + "</td>" + "<td>" + mRes.getTestResult() + "</td>" + "<td>" + mRes.getUnit() + "</td>" + "</tr>";

        }

        mHtmlString = mHtmlString + "</table>\n" +
                "</br>" +
                "</br>" +
                "\n" +
                "\n" +
                "<p> </p>" + "\n" +
                "<p>  </p>" + "\n" +
                "<span>Thanks</span>" + "\n" +
                "<p>" + checkUser() + "</p>" + "\n" +
                "<p>" + adress() + "</p>" + "\n" +
                "<p>" + phone() + "</p>" + "\n" +
                "</body>\n" +
                "</html>";


        if (notesList.size() > 0) {
            LongOperation mOperation = new LongOperation(s, "Water Test Results", mHtmlString);
            mOperation.execute();
            Toast.makeText(getApplicationContext(), "Mail sent to " + s, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "No Results Recoreded", Toast.LENGTH_LONG).show();
        }

    }

    private String phone() {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        String name = mSharedpref.getString("phone", "");
        return "" + name + "";
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public String checkUser() {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        String name = mSharedpref.getString("name", "");
        return "" + name + "";
    }

    public String adress() {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        String name = mSharedpref.getString("adress", "");
        return name;
    }


    public boolean chekNet(){
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
           return true;
        }else{
           return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         Constants.locale    = getApplicationContext().getResources().getConfiguration().getLocales().get(0);
        }
        else{
            Constants.locale =    getResources().getConfiguration().locale;
        }
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
