package com.e.esayVan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RequestAcceptDialog extends AppCompatDialogFragment {

    private EditText editText;
    private RequestAcceptDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle("Fees").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    String fee=editText.getText().toString();
                    listener.applyText(fee);

            }
        });
        editText=view.findViewById(R.id.edtFees);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener=(RequestAcceptDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement RequestAcceptDilogListner");
        }

    }

    public interface RequestAcceptDialogListener{
        void applyText(String fees);
    }

}
