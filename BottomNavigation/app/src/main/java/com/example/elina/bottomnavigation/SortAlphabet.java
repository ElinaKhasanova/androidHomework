package com.example.elina.bottomnavigation;

import java.util.Comparator;

public class SortAlphabet implements Comparator<Book> {

    @Override
    public int compare(Book bookFirst, Book bookSecond) {
        String nameFirst = bookFirst.getName();
        String nameSecond = bookSecond.getName();
        return bookFirst.compareTo(bookSecond);
    }
}
