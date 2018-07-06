package ramakrishna.watertest_image.hardness;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.utils.Constants;

public class HardnessAdapter extends PagerAdapter {
    // Declare Variables
    Activity context;
    ArrayList<String> name;
    ArrayList<Integer> pointer;
    ArrayList<Integer>images;
    int i = 0;
    LayoutInflater inflater;
    ArrayList<String> names;
    static ImageView imgflag;

    Handler myhHandler = new Handler();

    public HardnessAdapter(Activity act, ArrayList<Integer> images, ArrayList<String> names) {
        context = act;
        this.images = images;
        this.names = names;
    }



    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    public float getPageWidth(int position) {
        return 1f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final TextView tv;
        ListView lv;
        name = new ArrayList<String>();
        pointer=new ArrayList<Integer>();
        LayoutInflater inflater = LayoutInflater.from(context);
        final View itemView = inflater.inflate(R.layout.glucometer_viewpager_item,
                container, false);

        imgflag = (ImageView) itemView.findViewById(R.id.flag);
        imgflag.setImageResource(images.get(position));
        tv = (TextView) itemView.findViewById(R.id.viewpager_text);
            tv.setText(names.get(position));





        // tv.setText(names[position]);

        // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // ////////////////////////////////////////////////////////////////////////////////////////////////

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager

        ((ViewPager) container).removeView((LinearLayout) object);
    }

}
