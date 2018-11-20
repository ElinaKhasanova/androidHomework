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
import android.widget.ProgressBar;
import android.widget.Toolbar;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private List<Book> books;
    private CVAdapter adapter;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private RecyclerView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        progressBar = view.findViewById(R.id.progressBar);
        toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        setBooks();
        adapter = new CVAdapter(getActivity(), books);
        list.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_by_name:
                sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
                adapter.updateData(books);
                break;
            case R.id.menu_by_id:
                sort(books, (book1, book2) -> book1.getId() - book2.getId()));
                adapter.updateData(books);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sort(List<Book> bookList, Comparator<Book> comparator) {
        Flowable.fromIterable(bookList)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .take(12)
                .sorted(comparator)
                .map(book -> new Book(book.getId(), book.getName(),
                        book.getDescription(), book.getPhoto()))
                .doOnSubscribe(this::showLoading)
                .toList()
                .doAfterTerminate(this::hideLoading)
                .doOnSuccess(newBooks -> adapter.updateData(newBooks))
                .subscribe();
    }

    private void showLoading(Subscription subscription) {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    private void setBooks() {
        books = new ArrayList<>();
        books.add(new Book(0,"Война и мир", "Художественная литература", R.drawable.kniha));
        books.add(new Book(1,"Android", "Обучение", R.drawable.kniha));
        books.add(new Book(2, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(3, "Math", "Обучение", R.drawable.kniha));
        books.add(new Book(4, "Biology", "Обучение", R.drawable.kniha));
        books.add(new Book(5, "Geography", "Обучение", R.drawable.kniha));
        books.add(new Book(6, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(7, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(8, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(9, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(10, "Russian", "Обучение", R.drawable.kniha));
        books.add(new Book(11, "Geometry", "Обучение", R.drawable.kniha));
        books.add(new Book(12, "Algebra", "Обучение", R.drawable.kniha));
        books.add(new Book(13, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(14, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(15, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(16, "English", "Обучение", R.drawable.kniha));
        books.add(new Book(17, "English", "Обучение", R.drawable.kniha));
    }
}