package com.example.elina.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private List<Book> books;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        RecyclerView list = view.findViewById(R.id.list);
        setBooks();

        CVAdapter adapter = new CVAdapter(getActivity(), books);
        list.setAdapter(adapter);

        return view;
    }

    private void setBooks() {
        books = new ArrayList<>();
        books.add(new Book("Война и мир", "Художественная литература", R.drawable.kniha));
        books.add(new Book("Android", "Обучение", R.drawable.kniha));
        books.add(new Book("English", "Обучение", R.drawable.kniha));
    }
}
