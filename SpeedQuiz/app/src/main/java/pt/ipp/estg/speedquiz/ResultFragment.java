package pt.ipp.estg.speedquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "points";
    private static final String ARG_PARAM2 = "correct";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mContentView;
    private FloatingActionButton fb;
    private TextView txtPoints, txtCorrect, txtWrong ,numberPoints, numberCorrect, numberWrong;

    public ResultFragment () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance (String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentView = inflater.inflate(R.layout.fragment_result, container, false);

        txtPoints=mContentView.findViewById(R.id.points);
        txtCorrect=mContentView.findViewById(R.id.correct);
        txtWrong=mContentView.findViewById(R.id.wrong);
        numberPoints=mContentView.findViewById(R.id.numberPoints);
        numberCorrect=mContentView.findViewById(R.id.numberCorrect);
        numberWrong=mContentView.findViewById(R.id.numberWrong);
        fb= mContentView.findViewById(R.id.floatingActionButtonFinish);
        Log.d("Parametros", String.valueOf(mParam2));
        numberPoints.setText(mParam1);
        numberCorrect.setText(mParam2);
        numberWrong.setText(String.valueOf(6-Integer.parseInt(mParam2)));


          fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_tag, new QuizFragment())
                        .commit();
            }
        });

        return mContentView;
    }
}