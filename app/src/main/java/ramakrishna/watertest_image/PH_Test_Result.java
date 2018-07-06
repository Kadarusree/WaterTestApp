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

public class PH_Test_Result extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11, btn_12, btn_13, btn_14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_ph__test__result);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio_ph_4);
        mediaPlayer.start();


        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_11 = (Button) findViewById(R.id.btn_11);
        btn_12 = (Button) findViewById(R.id.btn_12);
        btn_13 = (Button) findViewById(R.id.btn_13);
        btn_14 = (Button) findViewById(R.id.btn_14);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_10.setOnClickListener(this);
        btn_11.setOnClickListener(this);
        btn_12.setOnClickListener(this);
        btn_13.setOnClickListener(this);
        btn_14.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_1:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_1.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_1.getText().toString(),getResources().getString(R.string.ph_low));
                break;
            case R.id.btn_2:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_2.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_2.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_3:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_3.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_3.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_4:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_4.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_4.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_5:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_5.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_5.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_6:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_6.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_6.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_7:
                playMedia(R.raw.audio_ph_7);
                Store_in_Pref(btn_7.getText().toString(),"ACIDIC","#F13F37");
                show_Dialog(btn_7.getText().toString(),getResources().getString(R.string.ph_low));

                break;
            case R.id.btn_8:
                playMedia(R.raw.audio_ph_6);
                Store_in_Pref(btn_8.getText().toString(),"NEUTRAL","#F3DA35");
                show_Dialog(btn_8.getText().toString(),getResources().getString(R.string.ph_normal));

                break;
            case R.id.btn_9:
                playMedia(R.raw.audio_ph_6);
                Store_in_Pref(btn_9.getText().toString(),"NEUTRAL","#F3DA35");
                show_Dialog(btn_9.getText().toString(),getResources().getString(R.string.ph_normal));

                break;
            case R.id.btn_10:
                playMedia(R.raw.audio_ph_6);
                Store_in_Pref(btn_10.getText().toString(),"NEUTRAL","#F3DA35");
                show_Dialog(btn_10.getText().toString(),getResources().getString(R.string.ph_normal));

                break;
            case R.id.btn_11:
                playMedia(R.raw.audio_ph_5);
                Store_in_Pref(btn_11.getText().toString(),"ALKALINE","#F13F37");
                show_Dialog(btn_11.getText().toString(),getResources().getString(R.string.ph_high));

                break;
            case R.id.btn_12:
                playMedia(R.raw.audio_ph_5);
                Store_in_Pref(btn_12.getText().toString(),"ALKALINE","#F13F37");
                show_Dialog(btn_12.getText().toString(),getResources().getString(R.string.ph_high));

                break;
            case R.id.btn_13:
                playMedia(R.raw.audio_7);
                Store_in_Pref(btn_13.getText().toString(),"ALKALINE","#F13F37");
                show_Dialog(btn_13.getText().toString(),getResources().getString(R.string.ph_high));

                break;
            case R.id.btn_14:
                playMedia(R.raw.audio_7);
                Store_in_Pref(btn_14.getText().toString(),"ALKALINE","#F13F37");
                show_Dialog(btn_14.getText().toString(),getResources().getString(R.string.ph_high));

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

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    public void Store_in_Pref(String value, String range, String color){
        SharedPreferences mSharedPreferences=getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferences.edit();
        mEditor.putString("ph_value",value);
        mEditor.putString("ph_range",range);
        mEditor.putString("ph_color_code",color);
        Result mResult = new Result(1, Constants.PH_TEST,"PH",value,"mg/l",color,"");
        DatabaseHelper mHelper = new DatabaseHelper(getApplicationContext());
        mHelper.insertResult(mResult);

        mEditor.commit();
    }

    public void show_Dialog(String value, String text){
       final Dialog d=new Dialog(PH_Test_Result.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv=(TextView)d.findViewById(R.id.tv_output_text);

        tv.setText("The pH value of this water sample is "+value+"\n\n"+text);

        Button ok=(Button)d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }
}
