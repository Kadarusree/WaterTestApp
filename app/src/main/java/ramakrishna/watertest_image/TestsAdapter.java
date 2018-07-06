package ramakrishna.watertest_image;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shreekanth on 9/17/2017.
 */

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<String> test_names;
    private ArrayList<Integer> test_images;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_layout, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
holder.title.setText(test_names.get(position));
        holder.img_test.setImageResource(test_images.get(position));


    }



    @Override
    public int getItemCount() {
        return test_names.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public CardView card_view;
        public ImageView img_test;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.test_name);
            card_view=(CardView)view.findViewById(R.id.card_view);
            img_test=(ImageView)view.findViewById(R.id.img_test);
            card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.card_view:
            }
        }
    }

    public TestsAdapter(Context mContext, ArrayList<String> testsList, ArrayList<Integer> test_images) {
        this.ctx = mContext;
        this.test_names = testsList;
        this.test_images=test_images;
    }
}
