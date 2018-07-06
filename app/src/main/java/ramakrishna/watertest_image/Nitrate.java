package ramakrishna.watertest_image;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Nitrate extends AppCompatActivity {


    MediaPlayer mMediaPlayer;


    TextView what_is_nitrate, start_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrate);
        mMediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.f_1);
       // mMediaPlayer.start();
        what_is_nitrate=(TextView)findViewById(R.id.btn_what_is_nitrate);
        start_test=(TextView)findViewById(R.id.btn_start_nitrate_test) ;
        what_is_nitrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nitrate_Description.class));
            }
        });

        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nitrate_Test.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
    }
}
