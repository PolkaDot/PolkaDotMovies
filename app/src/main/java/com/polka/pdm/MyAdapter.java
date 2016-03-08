package com.polka.pdm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Yami on 2/23/2016.
 * helper for recyclerview for movie
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Movie[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;
        private Movie mItem;
        private final Context context;

        public ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            v.setOnClickListener(this);
            mTextView = (TextView) v.findViewById(R.id.movieTitleTextView);
        }

        public void setItem(Movie item) {
            mItem = item;
            if (item != null) {
                mTextView.setText(item.getTitle());
            }
        }

        //implements when a movie is clicked
        @Override
        public void onClick(View view) {
            if (mItem != null) {
                Log.d("CLICK", "on Click" + mItem.getTitle());// Switch activities
                // PutExtra Movie
                Intent intent = new Intent(context, ViewMovie.class);
                intent.putExtra("movie", mItem);

                // PutExtra User
                User user = null;
                if (context instanceof SearchMovies) {
                   user = ((SearchMovies)context).getUser();
                }

                if (user != null) {
                    intent.putExtra("user", user);
                }
                // start next activity
                context.startActivity(intent);
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Movie[] myDataset) {
        mDataset = myDataset;
    }

    // Sets the dataset
    public void setData (Movie[] myDataset) {
        mDataset = myDataset;
        // notify adapter that the data has changed
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setItem(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.length;
        }
        return 0;
    }


}
