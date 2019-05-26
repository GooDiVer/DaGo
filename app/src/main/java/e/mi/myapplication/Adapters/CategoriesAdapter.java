package e.mi.myapplication.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.mi.myapplication.Net.Category;
import e.mi.myapplication.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    List<Category> categories;

    public void clear(){
        if(categories != null)
            categories.clear();
    }
    public void addAll(List<Category> categories) {
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoriesAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_category, viewGroup,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.text.setText(categories.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public CategoryViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.categoryItemText);
        }
    }
}
