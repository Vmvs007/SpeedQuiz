package pt.ipp.estg.speedquiz;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pt.ipp.estg.speedquiz.FireBaseAuth.Utilizador;
import pt.ipp.estg.speedquiz.FireBaseAuth.UtilizadorViewModel;


public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context mContext;

    private View mContentView;

    private DatePickerDialog datePickerDialog;

    private Calendar calendar;

    private Date date;

    private EditText edBirth;
    private EditText edName;
    private EditText edAddress;
    private EditText edCont;
    private Button cl;
    private Button edit;

    private List<Utilizador> utilList;
    private UtilizadorViewModel utilizadorViewModel;
    private Utilizador util;

    SharedPreferences mUser;
    SharedPreferences.Editor mEditor;





    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mContext=getActivity();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContentView = inflater.inflate(R.layout.perfil, container, false);
        utilizadorViewModel= ViewModelProviders.of(this).get(UtilizadorViewModel.class);
        mUser = PreferenceManager.getDefaultSharedPreferences(mContext);
        mEditor = mUser.edit();


        edBirth=mContentView.findViewById(R.id.textView143);
        edName=mContentView.findViewById(R.id.textView14);
        edAddress=mContentView.findViewById(R.id.textView142);
        edCont=mContentView.findViewById(R.id.textView1431);
        cl=mContentView.findViewById(R.id.button10);
        edit=mContentView.findViewById(R.id.button8);
        calendar= Calendar.getInstance();

        utilizadorViewModel.getUtilizadorList().observe(getViewLifecycleOwner(), new Observer<List<Utilizador>>() {
            @Override
            public void onChanged(List<Utilizador> eventList) {
                utilList = eventList;

                for(Utilizador u:utilList){
                    util=u;
                }

                edBirth.setText(String.valueOf(util.getData_nascimento()));
                edName.setText(util.getNome());
                edAddress.setText(util.getMorada());
                edCont.setText(String.valueOf(util.getContacto()));
                date=util.getData_nascimento();

                edit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        util.setNome(edName.getText().toString());
                        util.setMorada(edAddress.getText().toString());
                        util.setData_nascimento(date);
                        util.setContacto(Integer.valueOf(edCont.getText().toString()));

                        utilizadorViewModel.update(util);
                        Toast.makeText(mContext, "EDITED!", Toast.LENGTH_SHORT).show();
                        mEditor.putString("name",util.getNome());
                        mEditor.putString("cont", String.valueOf(util.getContacto()));
                        mEditor.commit();
                    }
                });
            }
        });





        datePickerDialog = new DatePickerDialog(mContext, R.style.DialogTheme,dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        edBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        cl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });





        return mContentView;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    date = calendar.getTime();
                    // edBirth.setText(date.toLocaleString().substring(0, 12));
                    String myFormat = "dd/MM/YYYY"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                    edBirth.setText(sdf.format(calendar.getTime()));

                }
            };




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String notif) {
        if (mListener != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {/*
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        void onButtonclick(String notif);
    }



}
