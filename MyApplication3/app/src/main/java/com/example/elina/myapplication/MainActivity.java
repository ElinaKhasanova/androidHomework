package com.example.elina.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBack {

    private RecyclerView recyclerView;
    private RVAdapter rvAdapter;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RVAdapter();
        recyclerView.setAdapter(rvAdapter);

        loadList();
    }

    private void loadList(){
        Collection<Book> books = getList();
        rvAdapter.setBooks(books);
    }

    private Collection<Book> getList(){
        books = new ArrayList<>();
        books.add(new Book("Android", "Обучение", R.drawable.kniha));
        books.add(new Book("Война и мир", "Художественная литература", R.drawable.kniha));
        books.add(new Book("Enlish", "Обучение", R.drawable.kniha));
        return books;
    }

    @Override
    public void callback(String name, String description) {
        Intent intent = new Intent(this, ActivityTwo.class);
        intent.putExtra("name", name);
        intent.putExtra("description",description);
        startActivity(intent);
    }
}
