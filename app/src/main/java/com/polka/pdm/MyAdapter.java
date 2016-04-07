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
 * @author Yami
 * @version 1.0
 * Provide views to RecyclerView in SearchMovie activity with data from the movie mDataSet.
 */
public final class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    /**
     * data set that holds all the movies
     */
    private Movie[] mDataset;

    /**
     * Provide a suitable constructor (depends on the kind of data set)
     *
     * @param myDataset data containing movies
     */
    public MyAdapter(Movie[] myDataset) {
        mDataset = myDataset; // because need to directly modify values in our recycler view
    }

    /**
     * Sets the dataset
     * @param myDataset dataset used to update the dataset in the adapter
     */
    public void setData (Movie[] myDataset) {
        mDataset = myDataset.clone();
        // notify adapter that the data has changed
        notifyDataSetChanged();
    }

    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent the parent view
     * @param viewType type of view
     * @return viewholder made with populated items
     */
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder the viewholder of the item
     * @param position the position in the view
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setItem(mDataset[position]);

    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     *
     * @return length of dataset. if null, 0
     */
    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.length;
        }
        return 0;
    }

    /**
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder
     */
    public static final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * current textview
         */
        private final TextView mTextView;
        /**
         * Current movie item
         */
        private Movie mItem;
        /**
         * current context
         */
        private final Context context;


        /**
         * Instantiates a new ViewHolder with current view
         * @param v the current view
         */
        private ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            v.setOnClickListener(this);
            mTextView = (TextView) v.findViewById(R.id.movieTitleTextView);
        }


        /**
         * Helper method to set the item in the recyclerview
         * @param item item to be set in the view
         */
        private void setItem(Movie item) {
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
                final Intent intent = new Intent(context, ViewMovie.class);
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
}
