package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter {
    List<Categories> categoriesList;

    public CategoriesAdapter(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        Categories categories = categoriesList.get(position);
        viewHolderClass.name.setText(categories.getName());
        viewHolderClass.budget.setText(String.valueOf(categories.getBudget()));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView name, budget;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvCategoryName);
            budget = itemView.findViewById(R.id.tvBudget);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle categoryInfo = new Bundle();
                    categoryInfo.putString("categoryName", name.getText().toString());
                    categoryInfo.putString("budget", budget.getText().toString());

                    Intent in = new Intent(itemView.getContext(), UpdateCategoryActivity.class);
                    in.putExtras(categoryInfo);
                    itemView.getContext().startActivity(in);
                }
            });
        }
    }
}
