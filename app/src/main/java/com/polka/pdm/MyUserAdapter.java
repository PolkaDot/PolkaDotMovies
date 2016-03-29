package com.polka.pdm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by C. Shih on 3/8/2016.
 * @author Christine
 * @version 1.0
 */
public class MyUserAdapter extends RecyclerView.Adapter<MyUserAdapter.ViewHolder>{

    private User[] mDataset;


    /**
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public Button banButton;
        public Button lockButton;
        public TextView mLock;
        public TextView mBan;

        User mItem;
        private final Context context;

        /**
         *
         * @param v
         */
        public ViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            mTextView = (TextView) v.findViewById(R.id.usernameTextView);
            banButton = (Button) v.findViewById(R.id.banButton);
            lockButton = (Button) v.findViewById(R.id.lockButton);
            mLock = (TextView) v.findViewById(R.id.isLockedTextView);
            mBan = (TextView) v.findViewById(R.id.isBannedTextView);
        }

        /**
         *
         * @param item
         */
        public void setItem(User item) {
            mItem = item;
            Log.d("ANOTHERTHING", "in setItem");
            if (item != null) {
                Log.d("SOMETHING", item.getUsername());
                mTextView.setText(item.getUsername());
                mLock.setText(((Integer)item.getIsLocked()).toString());
                mBan.setText(((Integer)item.getIsBanned()).toString());
            }
        }

    }

    /**
     * Provide a suitable constructor (depends on the kind of dataset)
     *
     * @param myDataset
     */
    public MyUserAdapter(User[] myDataset) {
        mDataset = myDataset;
    }

    /**
     * Sets the dataset
     *
     * @param myDataset
     */
    public void setData (User[] myDataset) {
        mDataset = myDataset;
        // notify adapter that the data has changed
        notifyDataSetChanged();
    }

    /**
     * Create new views (invoked by the layout manager)
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycler, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder
     * @param position
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
                UserRepo repo = new UserRepo(v.getContext());
                if (holder.mItem != null && holder.mItem.getUsername().equals("admin")) {
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
                UserRepo repo = new UserRepo(v.getContext());
                if (holder.mItem != null) {
                    if (holder.mItem.getUsername().equals("admin")) {
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
}
