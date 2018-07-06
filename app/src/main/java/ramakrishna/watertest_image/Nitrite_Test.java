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

public class Nitrite_Test extends AppCompatActivity {
    Button btn_nitratetest_complete;

    MediaPlayer mediaPlayer;

    TextView step1, step2, step3;

    Handler myHandler;
    Runnable mRunnable;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrite__test);

        step1=(TextView)findViewById(R.id.nitrite_step1);
        step2=(TextView)findViewById(R.id.nitrite_step2);
        step3=(TextView)findViewById(R.id.nitrite_step3);


        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.nitrite_1);
        mediaPlayer.start();
        btn_nitratetest_complete=(Button) findViewById(R.id.btn_nitritetest_complete);

        btn_nitratetest_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nitrite_Result.class));
            }
        });


        myHandler=new Handler();

        mRunnable=new Runnable() {
            @Override
            public void run() {
                switch (i){
                    case 3:
                        step1.setTextColor(getResources().getColor(R.color.color_green));
                        break;
                    case 7:
                        step2.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 14:
                        step3.setTextColor(getResources().getColor(R.color.color_green));

                        break;
                    case 19:
                        btn_nitratetest_complete.setBackgroundColor(getResources().getColor(R.color.color_green));

                        break;
                }
                update_timer();
            }
        };
        update_timer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
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
}
