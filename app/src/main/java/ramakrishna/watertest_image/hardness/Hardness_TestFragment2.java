package ramakrishna.watertest_image.hardness;

import android.app.Dialog;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class Hardness_TestFragment2 extends Fragment {

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


        if (Constants.selected_type==1)
        {
            images.add(R.drawable.hard_water);
            images.add(R.drawable.test_done);
            names.add(getResources().getString(R.string.hard_water));
            names.add(getResources().getString(R.string.total_hardness_5));

        }
        else if (Constants.selected_type==2)
        {
            images.add(R.drawable.soft_water);
            images.add(R.drawable.test_done);
            names.add(getResources().getString(R.string.soft_water));
            names.add(getResources().getString(R.string.total_hardness_5));

        }
        else if (Constants.selected_type==3)
        {
            images.add(R.drawable.very_hard_water);
            images.add(R.drawable.test_done);
            names.add(getResources().getString(R.string.very_hard_water));
            names.add(getResources().getString(R.string.total_hardness_5));

        }





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


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                pos = arg0;

                if (pos==1){
                    showDialog();
                }
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



    public void playAudio(int position){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        switch (position){

            case 0:
                if (Constants.selected_type==1){
                    mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardwater);
                    mediaPlayer.start();

                }
                else if(Constants.selected_type==2){
                    mediaPlayer= MediaPlayer.create(getActivity(),R.raw.softwater);
                    mediaPlayer.start();

                }
                else if(Constants.selected_type==3){
                    mediaPlayer= MediaPlayer.create(getActivity(),R.raw.very_hard_water);
                    mediaPlayer.start();

                }
                break;
            case 1:
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hardness_6);
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


    public void showDialog() {
        final Dialog dialog = new Dialog(new ContextThemeWrapper(getActivity(), R.style.DialogSlideAnim));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_value);


        Button mButton = (Button) dialog.findViewById(R.id.mSubmit);
        final EditText mEditText = (EditText) dialog.findViewById(R.id.mValue);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int drops = Integer.parseInt(mEditText.getText().toString());
                int value = drops * 10;
                if (value < 300) {
                    playResult(value, R.raw.hardness_normal, "#F3DA35");
                    show_Dialog(value+"", getActivity().getResources().getString(R.string.hardness_normal));


                } else if (value >= 300) {
                    playResult(value, R.raw.hardness_high, "#F13F37");
                    show_Dialog(value+"", getActivity().getResources().getString(R.string.hardness_high));


                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void playResult(int value, int id, String color) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(getActivity(), id);
        Result mResult = new Result(1, Constants.HARDNESSEST, "TOTAL HARDNESS", value + "", "PPM", color, "");
        DatabaseHelper mHelper = new DatabaseHelper(getActivity());
        mHelper.insertResult(mResult);

        mediaPlayer.start();

    }


    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(getActivity());
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);

        tv.setText("The Total Hardness value of this water sample is " + value + "\n\n" + text);

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
