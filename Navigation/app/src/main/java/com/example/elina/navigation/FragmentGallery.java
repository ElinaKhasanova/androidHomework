package com.example.elina.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentGallery extends Fragment {

    TextView tvGallery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_gallery, container, false);
        tvGallery = view.findViewById(R.id.tv_gallery);
        return view;
    }
}
