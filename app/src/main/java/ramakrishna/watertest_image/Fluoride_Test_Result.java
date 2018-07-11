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

import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

public class Fluoride_Test_Result extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_fluoride__test__result);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.flouride_4);
        mediaPlayer.start();


        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_1:
                playMedia(R.raw.flouride_5);
                Store_in_Pref("0.0", "NORMAL","#F3DA35");
                show_Dialog("0.0", getResources().getString(R.string.fl_normal));

                break;
            case R.id.btn_2:
                playMedia(R.raw.flouride_5);
                Store_in_Pref("1", "NORMAL","#F3DA35");
                show_Dialog("1", getResources().getString(R.string.fl_normal));

                break;
            case R.id.btn_3:
                playMedia(R.raw.flouride_5);
                Store_in_Pref("1.5", "NORMAL","#F3DA35");
                show_Dialog("1.5", getResources().getString(R.string.fl_normal));
                break;
            case R.id.btn_4:
                playMedia(R.raw.flouride_7);
                Store_in_Pref("2", "HIGH","#F13F37");
                show_Dialog("2", getResources().getString(R.string.fl_high));

                break;
            case R.id.btn_5:
                playMedia(R.raw.flouride_7);
                Store_in_Pref("3", "HIGH","#F13F37");
                show_Dialog("3", getResources().getString(R.string.fl_high));

                break;
            case R.id.btn_6:
                playMedia(R.raw.flouride_7);
                Store_in_Pref("5", "HIGH","#F13F37");
                show_Dialog("5", getResources().getString(R.string.fl_high));


        }

    }


    public void playMedia(int tone) {
        if (mediaPlayer != null || mediaPlayer.isPlaying()) {
            mediaPlayer.release();
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
        mediaPlayer.release();
    }

    public void Store_in_Pref(String value, String range, String color) {
        SharedPreferences mSharedPreferences = getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("fl_value", value);
        mEditor.putString("fl_range", range);
        mEditor.putString("fl_color_code", color);

        Result mResult = new Result(1, Constants.FLOURIDE_TEST,"Flouride",value,"mg/l",color,"");
        DatabaseHelper mHelper = new DatabaseHelper(getApplicationContext());
        mHelper.insertResult(mResult);
        mEditor.commit();
    }

    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(Fluoride_Test_Result.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);

        tv.setText(getResources().getString(R.string.fl_res_text)+" "+ value + "\n\n" + text);

        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();

                mediaPlayer.release();
            }
        });
        d.show();
    }
}
