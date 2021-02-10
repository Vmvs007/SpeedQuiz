package pt.ipp.estg.speedquiz.FireBaseAuth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Date;

import pt.ipp.estg.speedquiz.R;

public class ContactAddDialog extends AppCompatDialogFragment {

    private ExampleDialogListener listener;
    private EditText edt_cont;
    private Utilizador util;
    private UtilizadorViewModel utilizadorViewModel;

    SharedPreferences mUser;
    SharedPreferences.Editor mEditor;

    public ContactAddDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.contact_add_dialog, null);

        mUser = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mUser.edit();

        utilizadorViewModel = ViewModelProviders.of(this).get(UtilizadorViewModel.class);

        edt_cont = (EditText) view.findViewById(R.id.editTextDialog);






        builder.setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edt_cont!=null) {
                            int cont=0;
                            if (!edt_cont.getText().toString().equals("")){
                                cont= Integer.valueOf(edt_cont.getText().toString());
                            }
                            util = new Utilizador("","",new Date(),cont);

                            //update event into the database
                            utilizadorViewModel.insert(util);
                            mEditor.putString("name",util.getNome());
                            mEditor.putString("cont", String.valueOf(util.getContacto()));
                            mEditor.commit();
                        }else{
                            ContactAddDialog addDialog = new ContactAddDialog();
                            addDialog.show(getFragmentManager(), "add cont dialog");
                        }
                    }
                });


        AlertDialog dialog=builder.create();

        dialog.show();

        Button sd = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        sd.setBackgroundColor(Color.RED);
        sd.setWidth(420);


        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
/*            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");*/
        }
    }

    public interface ExampleDialogListener {
    }
}
