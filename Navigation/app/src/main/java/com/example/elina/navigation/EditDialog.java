package com.example.elina.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import java.util.Objects;

public class EditDialog extends DialogFragment {
    EditText etLogin;
    EditText etEmail;
    DataTransmit dataTransmit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            dataTransmit = (DataTransmit) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }

    @NonNull
    public android.app.Dialog onCreateDialog(@Nullable Bundle saveInstanceState) {
        super.onCreateDialog(saveInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_dialog, null);
        etLogin = view.findViewById(R.id.edit_login);
        etEmail = view.findViewById(R.id.edit_email);

        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        adb.setTitle("Введите данные").setView(view)
                .setPositiveButton("save", (dialog, which) -> {
                    String login = etLogin.getText().toString();
                    String eMail = etEmail.getText().toString();
                    dataTransmit.transmit(login, eMail);
                })
                .setNegativeButton("dismiss", (dialog, which) -> dismiss());
        return adb.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataTransmit = null;
    }
}
