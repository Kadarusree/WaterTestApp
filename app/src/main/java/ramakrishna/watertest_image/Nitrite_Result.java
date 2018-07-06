package ramakrishna.watertest_image;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Nitrite_Result extends AppCompatActivity implements View.OnClickListener {
    Button r1, r2, r3, r4, r5, r6;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrite__result);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.na_ni_3);
        mediaPlayer.start();

        r1 = (Button) findViewById(R.id.btn_nitrite_r1);
        r2 = (Button) findViewById(R.id.btn_nitrite_r2);
        r3 = (Button) findViewById(R.id.btn_nitrite_r3);
        r4 = (Button) findViewById(R.id.btn_nitrite_r4);
        r5 = (Button) findViewById(R.id.btn_nitrite_r5);
        r6 = (Button) findViewById(R.id.btn_nitrite_r6);

        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
        r6.setOnClickListener(this);
    }

    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(Nitrite_Result.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);
        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);
        tv.setText("The Nitrite value of this water sample is " + value + "\n\n" + text);
        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nitrite_r1:
                playMedia(R.raw.nitrite_2);
                Store_in_Pref("0.0", "NORMAL","#F3DA35");
                show_Dialog("0.0", getResources().getString(R.string.nitrite_safe));

                break;
            case R.id.btn_nitrite_r2:
                playMedia(R.raw.nitrite_2);
                Store_in_Pref("0.5", "NORMAL","#F3DA35");

                show_Dialog("0.5", getResources().getString(R.string.nitrite_safe));

                break;

            case R.id.btn_nitrite_r3:
                playMedia(R.raw.nitrite_2);
                Store_in_Pref("1", "NORMAL","#F3DA35");

                show_Dialog("1", getResources().getString(R.string.nitrite_safe));

                break;
            case R.id.btn_nitrite_r4:
                playMedia(R.raw.nitrite_2);
                Store_in_Pref("2", "NORMAL","#F3DA35");

                show_Dialog("2", getResources().getString(R.string.nitrite_safe));

                break;
            case R.id.btn_nitrite_r5:
                playMedia(R.raw.nitrite_2);
                Store_in_Pref("3", "NORMAL","#F3DA35");

                show_Dialog("3", getResources().getString(R.string.nitrite_safe));

                break;
            case R.id.btn_nitrite_r6:
                playMedia(R.raw.na_ni_4);
                Store_in_Pref("5", "HIGH","#F13F37");

                show_Dialog("5", getResources().getString(R.string.nitrite_danger));
                break;
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
        mEditor.putString("ni_value", value);
        mEditor.putString("ni_range", range);
        mEditor.putString("ni_color_code", color);


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
