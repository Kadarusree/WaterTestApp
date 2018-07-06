package ramakrishna.watertest_image;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PH_Test_Activity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private static int SPLASH_TIME_OUT = 3000;

    Handler myHandler;
    Runnable mRunnable;
    int i=0;

    TextView step1, step2, step3;
    Button btn_testComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_ph__test_);

        step1=(TextView)findViewById(R.id.tv_step1);
        step2=(TextView)findViewById(R.id.tv_step2);
        step3=(TextView)findViewById(R.id.tv_step3);

        btn_testComplete=(Button) findViewById(R.id.btn_test_complete);

        btn_testComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // startActivity(new Intent(getApplicationContext(),PH_Test_Result.class));

            startActivity(new Intent(getApplicationContext(),PH_Test_Result_new.class));

           // startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });

        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.audio_3);
        mediaPlayer.start();


        myHandler=new Handler();

        mRunnable=new Runnable() {
            @Override
            public void run() {
                switch (i){
                    case 8:
                        step1.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 13:
                        step2.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 20:
                        step3.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 25:
                        btn_testComplete.setBackgroundColor(getResources().getColor(R.color.color_green));

                        break;
                }
                update_timer();
            }
        };
        update_timer();

    }

    public void update_timer(){
        i++;
        if (i<27){
            myHandler.postDelayed(mRunnable,1000);
        }
        else {
            myHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        i=0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}
