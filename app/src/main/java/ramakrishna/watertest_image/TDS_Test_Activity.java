package ramakrishna.watertest_image;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TDS_Test_Activity extends AppCompatActivity {

    TextView td_header_text;

    MediaPlayer mediaPlayer;

    TextView td_1, td_2, td_3, td_4, td_5, td_6;
    Button submit;
    EditText tds_value;


    Handler myHandler;
    Runnable mRunnable;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tds__test_);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        td_header_text = (TextView) findViewById(R.id.td_header_text);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tds_1);
        mediaPlayer.start();

        String text = "<font color=#000000>A </font><font color=#000000><b>TDS Meter</b></font> <font color=#000000>the</font> <font color=#0E76BD><u>total dissolved solids</u></font> ";
        String text2 = "<font color=#000000>(TDS) of a solution, i.e. the concentration of dissolved solid particles.</font>";
        td_header_text.setText(Html.fromHtml(text + text2));

        td_1 = (TextView) findViewById(R.id.tds_1);
        td_2 = (TextView) findViewById(R.id.tds_2);
        td_3 = (TextView) findViewById(R.id.tds_3);
        td_4 = (TextView) findViewById(R.id.tds_4);
        td_5 = (TextView) findViewById(R.id.tds_5);
        td_6 = (TextView) findViewById(R.id.tds_6);
        submit = (Button) findViewById(R.id.btn_submit);
        tds_value = (EditText) findViewById(R.id.edt_td_value);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tds_value.getText().length() < 1) {
                    tds_value.setError("Enter TDS Value");
                } else if (Integer.parseInt(tds_value.getText().toString()) < 80) {
                    playMedia(R.raw.tds_80);
                    Store_in_Pref(tds_value.getText().toString(),"LOW","#F13F37");
                    Toast.makeText(getApplicationContext(),"LOW TDS CONTENT",Toast.LENGTH_LONG).show();

                } else if (Integer.parseInt(tds_value.getText().toString()) > 79 && Integer.parseInt(tds_value.getText().toString()) < 499) {
                    playMedia(R.raw.tds_80_500);
                    Store_in_Pref(tds_value.getText().toString(),"NORMAL","#F3DA35");
                    Toast.makeText(getApplicationContext(),"NORMAL TDS CONTENT",Toast.LENGTH_LONG).show();


                } else if (Integer.parseInt(tds_value.getText().toString()) > 499) {
                    playMedia(R.raw.tds_500);
                    Store_in_Pref(tds_value.getText().toString(),"HIGH","#F13F37");
                    Toast.makeText(getApplicationContext(),"HIGH TDS CONTENT",Toast.LENGTH_LONG).show();

                }
            }
        });


        myHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                switch (i) {
                    case 11:
                        td_1.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 15:
                        td_2.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 18:
                        td_3.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 23:
                        td_4.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 28:
                        td_5.setTextColor(getResources().getColor(R.color.color_green));
                        tds_value.setFocusable(true);

                        break;
                    case 33:
                        td_6.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 36:
                        submit.setBackgroundColor(getResources().getColor(R.color.color_green));

                        break;
                }
                update_timer();
            }
        };
        update_timer();
    }

    public void update_timer() {
        i++;
        if (i < 39) {
            myHandler.postDelayed(mRunnable, 1000);
        } else {
            myHandler.removeCallbacks(mRunnable);
        }
    }

    public void playMedia(int tone) {
        if (mediaPlayer != null || mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), tone);
            mediaPlayer.start();

        }
    }
    public void Store_in_Pref(String value, String range, String color){
        SharedPreferences mSharedPreferences=getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferences.edit();
        mEditor.putString("tds_value",value);
        mEditor.putString("tds_range",range);
        mEditor.putString("tds_color_code",color);


        mEditor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}

