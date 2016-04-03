package com.polka.pdm;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Christine on 3/8/2016.
 * @author Christine
 * @version 1.0
 */
public class MyUserAdapter extends RecyclerView.Adapter<MyUserAdapter.ViewHolder>{

    /**
     * array of users to display for recycler view
     */
    private final User[] mDataset;




    /**
     * Provide a suitable constructor (depends on the kind of dataset)
     *     * constructor for creating a user adapter for admin functionality

     *
     * @param myDataset that the recycler view is user
     */
    public MyUserAdapter(User[] myDataset) {
        mDataset = myDataset.clone();
    }


//    /**
//     * sets the set of users of recycler view in admin functionality
//     *
//     * @param myDataset of users that the recycler view is using
//     */
//    // Sets the dataset
//    public void setData (User[] myDataset) {
//        mDataset = myDataset.clone();
//        // notify adapter that the data has changed
//        notifyDataSetChanged();
//    }

    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent parent of the view
     * @param viewType type of view adapter is using
     * @return  new view holder with populated items
     */
    @Override
    public MyUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycler, parent, false);
        // set the view's size, margins, padding, and layout parameters

        return new ViewHolder(v);
//        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder the view holder of item
     * @param position position of the view holder
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setItem(mDataset[position]);
        holder.banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ban Button Pressed", "ban is pressed");
                final UserRepo repo = new UserRepo(v.getContext());
                if (holder.mItem != null && ("admin").equals(holder.mItem.getUsername())) {
                    return;
                }
                if (holder.mItem != null) {
                    if (holder.mItem.getIsBanned() == 0) {
                        holder.mItem.setIsBanned(1);
                    } else if (holder.mItem.getIsBanned() == 1) {
                        holder.mItem.setIsBanned(0);
                    }
                    repo.setBanned(holder.mItem.getUsername(), holder.mItem.getIsBanned());
                    notifyItemChanged(holder.getAdapterPosition());
                }

            }
        });
        holder.lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Unlock Button Pressed", "unlock is pressed");
                final UserRepo repo = new UserRepo(v.getContext());
                if (holder.mItem != null) {
                    if (("admin").equals(holder.mItem.getUsername())) {
                        return;
                    }
                    holder.mItem.setIsLock(0);
                    repo.setLock(holder.mItem.getUsername(), holder.mItem.getIsLocked());
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });

    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     *
     * @return the length (0 if null)
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
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        /**
         * text view to listen to
         */
        private final TextView mTextView;
        /**
         * ban button to listen to
         */
        private final Button banButton;
        /**
         * lock button to listen to
         */
        private final Button lockButton;
        /**
         * lock view being updated
         */
        private final TextView mLock;
        /**
         * ban view being updated
         */
        private final TextView mBan;

        /**
         * user to be displayed and modified
         */
        private User mItem;
//        private final Context context;


        /**
         * sets up view holder
         * @param v view that it is currently showing
         */
        public ViewHolder(View v) {
            super(v);
//            context = itemView.getContext();
            mTextView = (TextView) v.findViewById(R.id.usernameTextView);
            banButton = (Button) v.findViewById(R.id.banButton);
            lockButton = (Button) v.findViewById(R.id.lockButton);
            mLock = (TextView) v.findViewById(R.id.isLockedTextView);
            mBan = (TextView) v.findViewById(R.id.isBannedTextView);
        }

        /**
         * sets the item in the view holder
         *
         * @param item the user that is to be set
         */
        public void setItem(User item) {
            mItem = item;
            Log.d("ANOTHER THING", "in setItem");
            if (item != null) {
                Log.d("SOMETHING", item.getUsername());
                mTextView.setText(item.getUsername());
                mLock.setText(String.format("%d", item.getIsLocked()));
                mBan.setText(String.format("%d", item.getIsBanned()));
            }
        }

    }
}
