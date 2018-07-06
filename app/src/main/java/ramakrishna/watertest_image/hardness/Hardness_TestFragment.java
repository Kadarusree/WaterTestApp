package ramakrishna.watertest_image.hardness;

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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ramakrishna.watertest_image.Fluoride_Test_Result;
import ramakrishna.watertest_image.Home;
import ramakrishna.watertest_image.MainActivity;
import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.adapters.ViewPagerAdapter;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class Hardness_TestFragment extends Fragment {

    // /// For View pager /////////////

    Dialog dialog_ViewPage;
    ViewPager viewPager;
    PagerAdapter adapter;
    ArrayList<Integer> images;
    ArrayList<String> names;
    LinearLayout llDots;
    Button btn;

    int pos = 0;
    Handler myHanldler = new Handler();

    // ///End for view pager //////////////
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ph_viewpager, null);
        images = new ArrayList<>();
        names = new ArrayList<>();

        getDialogForViewPager(v);
        return v;
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
        TextView help = (TextView) v.findViewById(R.id.btn_ph_complete);
        final LinearLayout parent_layout = (LinearLayout) v.findViewById(R.id.parent_layout);
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // startActivity(new Intent(getActivity(), Fluoride_Test_Result.class));

            }
        });


        llDots = (LinearLayout) v.findViewById(R.id.llDots);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        images.add(R.drawable.ph_1);
        images.add(R.drawable.total_hd_2);
        images.add(R.drawable.total_hd_3);
        images.add(R.drawable.test_done);


        names.add(getResources().getString(R.string.total_hardness_1));
        names.add(getResources().getString(R.string.total_hardness_2));
        names.add(getResources().getString(R.string.total_hardness_3));
        names.add(getResources().getString(R.string.total_hardness_4));


        adapter = new HardnessAdapter(getActivity(), images, names);
        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);


        getSlide();

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
                if (posi<6){
                    ((ImageView) llDots.findViewWithTag(posi)).setSelected(true);

                }
                playAudio(posi);
                if (posi == 3) {
                    selectWater();
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
//startActivity(new Intent(getActivity(),SpeedViewAct.class));

                if (pos < images.size()) {
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
            if (pos < images.size()) {
                getSlide();
                pos++;
            }
        }
    };


    public void selectWater() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Please Select your Water");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.rd_1);
        final RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.rd_2);
        final RadioButton rd3 = (RadioButton) dialog.findViewById(R.id.rd_3);
        final TextView td_header_text = (TextView) dialog.findViewById(R.id.header);

        td_header_text.setText("Select Your Water");

        rd1.setText("Hard Water");
        rd2.setText("Softwater");
        rd3.setText("Very Hard Water");


        Button btn = (Button) dialog.findViewById(R.id.btn_1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(getActivity(),HardnessPart2.class));

            }
        });

        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rd2.setChecked(false);
                    rd3.setChecked(false);


                    Constants.selected_type = 1;
                }

            }
        });
        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rd1.setChecked(false);
                    rd3.setChecked(false);


                    Constants.selected_type = 2;

                }

            }
        });
        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rd1.setChecked(false);
                    rd2.setChecked(false);

                    Constants.selected_type = 3;

                }

            }
        });


        dialog.show();


    }


    public void playAudio(int position){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        switch (position){

            case 0:
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardness_1);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardness_2);
                mediaPlayer.start();
                break;
            case 2:

                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardness_3);
                mediaPlayer.start();
                break;
            case 3:
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardness_4);
                mediaPlayer.start();
                break;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
    }

}
