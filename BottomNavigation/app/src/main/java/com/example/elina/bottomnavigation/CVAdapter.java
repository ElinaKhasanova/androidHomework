package com.example.elina.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CVAdapter extends RecyclerView.Adapter<CVAdapter.BookViewHolder> {
    CallBack callBack;

    public CVAdapter(CallBack callBack) {
        this.callBack = callBack;
    }

    private List<Book> books = new ArrayList<>();

    class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView nameBook;
        private TextView descriptionBook;
        private ImageView photoBook;

        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            nameBook = itemView.findViewById(R.id.text_book_name);
            descriptionBook = itemView.findViewById(R.id.text_book_description);
            photoBook = itemView.findViewById(R.id.book_photo);
        }

        public void bind(Book book){
            nameBook.setText(book.getName());
            descriptionBook.setText(book.getDescription());
            photoBook.setImageResource(book.getPhoto());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.callback(nameBook.getText().toString(), descriptionBook.getText().toString());
                }
            });
        }
    }

    public void updateData(ArrayList<Book> newData) {

        BookDiffUtil callback = new BookDiffUtil(newData, );
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback, true);

        diffResult.dispatchUpdatesTo(this);
        .clear();
        .addAll(newData);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        bookViewHolder.bind(books.get(i));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
