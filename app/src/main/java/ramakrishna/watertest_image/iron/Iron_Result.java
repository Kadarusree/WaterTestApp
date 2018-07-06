package ramakrishna.watertest_image.iron;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

public class Iron_Result extends AppCompatActivity implements View.OnClickListener {

    Button r1, r2, r3, r4, r5, r6;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_iron__result);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.iron_5);
        mediaPlayer.start();

        r1 = (Button) findViewById(R.id.btn_iron_r1);
        r2 = (Button) findViewById(R.id.btn_iron_r2);
        r3 = (Button) findViewById(R.id.btn_iron_r3);
        r4 = (Button) findViewById(R.id.btn_iron_r4);
        r5 = (Button) findViewById(R.id.btn_iron_r5);

        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           case R.id.btn_iron_r1:
                playMedia(R.raw.iron_6);
                Store_in_Pref("0.0", "NORMAL", "#F3DA35");

                show_Dialog("0.1", getResources().getString(R.string.iron_normal));

                break;
            case R.id.btn_iron_r2:
                playMedia(R. raw.iron_6);

                Store_in_Pref("0.3", "NORMAL", "#F3DA35");

                show_Dialog("0.3", getResources().getString(R.string.iron_normal));

                break;
            case R.id.btn_iron_r3:
                playMedia(R.raw.iron_7);

                Store_in_Pref("1", "HIGH", "#F13F37");

                show_Dialog("1", getResources().getString(R.string.iron_high));

                break;
            case R.id.btn_iron_r4:
                playMedia(R.raw.iron_7);

                Store_in_Pref("3", "HIGH", "#F13F37");

                show_Dialog("3", getResources().getString(R.string.iron_high));

                break;
            case R.id.btn_iron_r5:
                playMedia(R.raw.iron_7);

                Store_in_Pref("5", "HIGH", "#F13F37");

                show_Dialog("5", getResources().getString(R.string.iron_high));

                break;

        }

    }


    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(Iron_Result.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);
        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);
        tv.setText(text);
        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }

    public void Store_in_Pref(String value, String range, String color) {
        SharedPreferences mSharedPreferences = getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("na_value", value);
        mEditor.putString("na_range", range);
        mEditor.putString("na_color_code", color);

        Result mResult = new Result(1, Constants.IRON_TEST,"IRON",value,"PPM",color,"");
        DatabaseHelper mHelper = new DatabaseHelper(getApplicationContext());
        mHelper.insertResult(mResult);
        mEditor.commit();
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

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}
