package com.example.elina.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private CardView cardView;
    private CVAdapter cvAdapter;
    private List<Book> books;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    setContentView(R.layout.item);
                    toolbar = findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);

//                    cardView = findViewById(R.id.cv);
//                    cardView.setLayoutManager(new LinearLayoutManager(this));
//                    cvAdapter = new CVAdapter(this);
//                    cardView.setAdapter(cvAdapter);

                    return true;

                case R.id.navigation_notifications:
                    setContentView(R.layout.view_page);
                    ViewPager pager = (ViewPager)findViewById(R.id.pager);
                    pager.setAdapter(new PageAdapter(getBaseContext(), getSupportFragmentManager()));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadList(){
        Collection<Book> books = getList();
        cvAdapter.setBooks(books);
    }

    private Collection<Book> getList(){
        books = new ArrayList<>();
        books.add(new Book("Android", "Обучение", R.drawable.kniha));
        books.add(new Book("Война и мир", "Художественная литература", R.drawable.kniha));
        books.add(new Book("English", "Обучение", R.drawable.kniha));
        return books;
    }
}
