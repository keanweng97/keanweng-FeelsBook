package com.example.keanweng_feelsbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<Emotion> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        final TextView title;
        final TextView subtitle;
        final TextView date;

        MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            title = v.findViewById(R.id.textView);
            subtitle = v.findViewById(R.id.textView3);
            date = v.findViewById(R.id.textView2);
        }

        @Override
        public void onClick(View v) {
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(getLayoutPosition());
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Emotion> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emotion_list_item, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Emotion emotion = mDataset.get(position);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        Date date = emotion.getDate();
        String f_date = df.format(date);

        holder.title.setText(emotion.getMoodtype());
        holder.subtitle.setText(emotion.getComment());
        holder.date.setText(f_date);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
