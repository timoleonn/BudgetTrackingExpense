package com.example.budgettrackingexpense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<String> {
    public CategoryAdapter(Context context, List<String> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);

            viewHolder = new ViewHolder(itemView);

            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }

        String name = getItem(position);

        viewHolder.tvCategoryName.setText(name);

        return itemView;

    }

    public class ViewHolder {
        TextView tvCategoryName;

        public ViewHolder(View itemView) {
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
