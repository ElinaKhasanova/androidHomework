package com.example.elina.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListFragment extends Fragment {

    private List<Book> books;
    private CVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        RecyclerView list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        setBooks();

        CVAdapter adapter = new CVAdapter(getActivity(),books);
        list.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sort, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_by_name:
                Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(Book book1, Book book2) {
                        return book1.getName().compareTo(book2.getName());
                    }
                });
                adapter.updateData(books);
                break;
            case R.id.menu_by_description:
                Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(Book book1, Book book2) {
                        return book1.getDescription().compareTo(book2.getDescription());
                    }
                });
                adapter.updateData(books);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBooks() {
        books = new ArrayList<>();
        books.add(new Book("Война и мир", "Художественная литература", R.drawable.kniha));
        books.add(new Book("Android", "Обучение", R.drawable.kniha));
        books.add(new Book("English", "Обучение", R.drawable.kniha));
    }
}
