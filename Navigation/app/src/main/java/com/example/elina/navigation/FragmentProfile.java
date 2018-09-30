package com.example.elina.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentProfile extends Fragment implements DataTransmit {
    TextView tvLogin;
    TextView tvEmail;
    Button editButton;
    String login = "login";
    String email = "email";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        tvLogin = view.findViewById(R.id.tv_login);
        tvEmail = view.findViewById(R.id.tv_email);
        editButton = view.findViewById(R.id.btn_edit);

        tvLogin.setText(login);
        tvEmail.setText(email);

        editButton.setOnClickListener(v -> {
            EditDialog editDialog = new EditDialog();
            editDialog.show(FragmentProfile.this.getChildFragmentManager(),"Введите данные");
        });
        return view;
    }

    @Override
    public void transmit(String login, String mail) {
        tvLogin.setText(login);
        tvEmail.setText(mail);
    }

    public void save(String login, String mail) {
        tvLogin.setText(login);
        tvEmail.setText(mail);
    }
}
