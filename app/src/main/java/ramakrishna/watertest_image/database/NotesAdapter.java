package ramakrishna.watertest_image.database;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ramakrishna.watertest_image.R;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
 
    private Context context;
    private List<Result> notesList;
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public TextView dot;
        public TextView timestamp;
        public TextView value;
        public MyViewHolder(View view) {
            super(view);
            note = (TextView) view.findViewById(R.id.note);
            dot = (TextView) view.findViewById(R.id.dot);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            value = (TextView) view.findViewById(R.id.res_value);
        }
    }
 
 
    public NotesAdapter(Context context, List<Result> notesList) {
        this.context = context;
        this.notesList = notesList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result note = notesList.get(position);
 
        holder.note.setText(note.getTestName());
        holder.value.setText(note.getTestResult()+" "+note.getUnit());
        // Displaying dot from HTML character code
    holder.dot.setBackgroundColor(Color.parseColor(note.getColor()));
        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimeStamp())+" GMT");
    }
 
    @Override
    public int getItemCount() {
        return notesList.size();
    }
 
    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
           //SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd yyyy HH:mm");

           // long d = Date.parse(fmtOut.format(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 5);
            calendar.add(Calendar.MINUTE, 30);
            return calendar.getTime()+"";

        } catch (ParseException e) {

        }

        return "";
    }
}