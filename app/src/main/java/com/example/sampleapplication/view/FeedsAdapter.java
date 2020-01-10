package com.example.sampleapplication.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sampleapplication.R;
import com.example.sampleapplication.data.modal.Row;

import java.util.List;

/**
 * Created by ramkrishna 20/01/2020
 */
public class FeedsAdapter extends BaseAdapter {

    private Context context;
    private List<Row> rowList;
    private static final int IMAGE_WIDTH = 600;
    private static final int IMAGE_HEIGHT = 500;

    FeedsAdapter(Context context, List<Row> rowList) {
        this.context = context;
        this.rowList = rowList;
    }

    public void updateListView(List<Row> rowList) {
        this.rowList = rowList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowList.size();
    }

    @Override
    public Object getItem(int i) {
        if (rowList != null) {
            return rowList.get(i);
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.feeds_itemview_layout, viewGroup,
                    false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Row row = (Row) getItem(i);
        viewHolder.bind(row, context);
        return view;
    }

    private static class ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageView;

        ViewHolder(View view) {
            titleTextView = view.findViewById(R.id.txt_title);
            descriptionTextView = view.findViewById(R.id.txt_description);
            imageView = view.findViewById(R.id.image_view);
        }

        void bind(Row row, Context context) {
            String title = row.getTitle();
            String description = row.getDescription();
            String imageURL = row.getImageHref();
            if (!TextUtils.isEmpty(title)) {
                titleTextView.setText(title);
            }
            if (!TextUtils.isEmpty(description)) {
                descriptionTextView.setText(description);
            }
            if (!TextUtils.isEmpty(imageURL)) {
                Glide.with(context)
                        .load(imageURL)
                        .centerCrop()
                        .placeholder(R.drawable.image_place_holder)
                        .apply(new RequestOptions().override(IMAGE_WIDTH, IMAGE_HEIGHT))
                        .error(R.drawable.image_error)
                        .into(imageView);
            }
        }
    }
}
