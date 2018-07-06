package ramakrishna.watertest_image.Chloride;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ramakrishna.watertest_image.R;

public class ChlorideTest extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    TextView ch_1, ch_2, ch_3, ch_4, ch_5, ch_6;
    Button submit;
    EditText ch_value;


    Handler myHandler;
    Runnable mRunnable;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chloride_test);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ch_1);
        mediaPlayer.start();


        ch_1 = (TextView) findViewById(R.id.ch_1);
        ch_2 = (TextView) findViewById(R.id.ch_2);
        ch_3 = (TextView) findViewById(R.id.ch_3);
        ch_4 = (TextView) findViewById(R.id.ch_4);
        ch_5 = (TextView) findViewById(R.id.ch_5);
        ch_6 = (TextView) findViewById(R.id.ch_6);
        submit = (Button) findViewById(R.id.btn_ch_submit);
        ch_value = (EditText) findViewById(R.id.edt_chloride_value);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int drops = Integer.parseInt(ch_value.getText().toString());
                int value = drops * 10;
                if (value < 250) {
                    playMedia(R.raw.ch_2);
                    Store_in_Pref(value+"", "NORMAL", "#F3DA35");
                    show_Dialog(value + "", getResources().getString(R.string.chloride_low));

                } else if (value > 250) {
                    playMedia(R.raw.ch_3);
                    Store_in_Pref(value+"", "HIGH", "#F13F37");
                    show_Dialog(value + "", getResources().getString(R.string.chloride_high));

                }
            }
        });


        myHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                switch (i) {
                    case 4:
                        ch_1.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 9:
                        ch_2.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 16:
                        ch_3.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 24:
                        ch_4.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 30:
                        ch_5.setTextColor(getResources().getColor(R.color.color_green));
                        ch_value.setFocusable(true);

                        break;
                    case 37:
                        ch_6.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 42:
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
        if (i < 44) {
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

    public void Store_in_Pref(String value, String range, String color) {
        SharedPreferences mSharedPreferences = getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("ch_value", value);
        mEditor.putString("ch_range", range);
        mEditor.putString("ch_color_code", color);


        mEditor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }


    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(ChlorideTest.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);

        tv.setText("The Chloride value of this water sample is " + value + "\n\n" + text);

        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }
}
