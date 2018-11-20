package com.example.elina.bottomnavigation;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class BookDiffUtil extends DiffUtil.Callback {

    private final List<Book> oldList;
    private final List<Book> newList;

    public BookDiffUtil(List<Book> oldList, List<Book> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Book oldProduct = oldList.get(oldItemPosition);
        Book newProduct = newList.get(newItemPosition);
        return oldProduct.getName() == newProduct.getName();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Book oldProduct = oldList.get(oldItemPosition);
        Book newProduct = newList.get(newItemPosition);
        return oldProduct.getName().equals(newProduct.getName())
                && oldProduct.getId() == newProduct.getId();
    }
}
