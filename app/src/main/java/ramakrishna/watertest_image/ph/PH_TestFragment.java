package ramakrishna.watertest_image.ph;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import ramakrishna.watertest_image.PH_Test_Result;
import ramakrishna.watertest_image.PH_Test_Result_new;
import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.adapters.ViewPagerAdapter;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class PH_TestFragment extends Fragment {

    // /// For View pager /////////////

    Dialog dialog_ViewPage;
    ViewPager viewPager;
    PagerAdapter adapter;
    int[] images;
    String[] names;
    LinearLayout llDots;
    Button btn;

    int pos = 0;
    Handler myHanldler = new Handler();

    TextView btn_ph_complete;

    // ///End for view pager //////////////

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ph_viewpager, null);
        btn_ph_complete = (TextView)v.findViewById(R.id.btn_ph_complete);
        btn_ph_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PH_Test_Result.class));
            }
        });

        getDialogForViewPager(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected void getDialogForViewPager(View v) {
        pos = 0;
        playAudio(0);
        dialog_ViewPage = new Dialog(getActivity());
        dialog_ViewPage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_ViewPage.setContentView(R.layout.ph_viewpager);
        dialog_ViewPage.setTitle("Steps to connect...");
        dialog_ViewPage.setCancelable(false);

        TextView connect = (TextView) v.findViewById(R.id.btn_restart);
        final TextView help = (TextView) v.findViewById(R.id.btn_ph_complete);
        final LinearLayout parent_layout = (LinearLayout) v.findViewById(R.id.parent_layout);
        help.setVisibility(View.INVISIBLE);
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PH_Test_Result.class));

            }
        });


        llDots = (LinearLayout) v.findViewById(R.id.llDots);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        images = new int[]{R.drawable.ph_1, R.drawable.ph_2, R.drawable.ph_3,
        };

        names = new String[]{getResources().getString(R.string.ph_test_step1),
                getResources().getString(R.string.ph_test_step2),
                getResources().getString(R.string.ph_test_step3)
        };

        adapter = new ViewPagerAdapter(getActivity(), images, names);
        viewPager.setAdapter(adapter);

       // getSlide();

        for (int i = 0; i < adapter.getCount(); i++) {
            // Toast.makeText(act, "toast:"+i, 2000).show();
            ImageButton imgDot = new ImageButton(getActivity());
            imgDot.setTag(i);
            imgDot.setImageResource(R.drawable.dot_selector);
            imgDot.setBackgroundResource(0);
            imgDot.setPadding(10, 10, 10, 10);
            imgDot.setPaddingRelative(5, 5, 5, 5);
            LayoutParams params = new LayoutParams(20, 20);
            imgDot.setLayoutParams(params);

            if (i == 0)
                imgDot.setSelected(true);

            llDots.addView(imgDot);
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int posi) {

                for (int i = 0; i < adapter.getCount(); i++) {
                    if (i != posi) {
                        ((ImageView) llDots.findViewWithTag(i)).setSelected(false);
                    }
                }
                ((ImageView) llDots.findViewWithTag(posi)).setSelected(true);

                playAudio(posi);

                if (posi==(names.length-1)){
                    help.setVisibility(View.VISIBLE);
                }
                else {
                    help.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                pos = arg0;

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (pos < images.length) {
                    pos++;
                    getSlide();

                }
            }
        });
        // dialog_ViewPage.show();
    }

    private void getSlide() {
        viewPager.setCurrentItem(pos);
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            if (pos < images.length) {
                getSlide();
                pos++;
            }
        }
    };

    public void playAudio(int position){
        if (mediaPlayer!=null ){
        mediaPlayer.release();
        }
        switch (position){

            case 0:
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.audio_ph_1);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.audio_ph_2);
                mediaPlayer.start();
                break;
            case 2:
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.audio_ph_3);
                mediaPlayer.start();
                break;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }





}
