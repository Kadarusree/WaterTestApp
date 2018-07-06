package ramakrishna.watertest_image.ph;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.NotesAdapter;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;
import ramakrishna.watertest_image.utils.MyDividerItemDecoration;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class PH_ResultsLog extends Fragment {
    private NotesAdapter mAdapter;
    private List<Result> notesList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TextView noNotesView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_test__reports,null);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        noNotesView=(TextView)v.findViewById(R.id.empty_notes_view);
        notesList.addAll(db.getTestResults(Constants.CURRENT_TEST));

        if (db.getTestResults(Constants.CURRENT_TEST).size()==0){
            noNotesView.setText("No Results Found");
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new NotesAdapter(getActivity(), notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(mAdapter);
        return v;
    }
}
