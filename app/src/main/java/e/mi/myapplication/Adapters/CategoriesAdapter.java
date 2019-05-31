package e.mi.myapplication.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.mi.myapplication.ExtraParameters;
import e.mi.myapplication.Fragments.EventFragment;
import e.mi.myapplication.MainActivity;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    List<Category> categories;
    Context mContext;

    public void clear(){
        if(categories != null)
            categories.clear();
    }
    public void addAll(List<Category> categories) {
        this.categories = categories;
    }
    public CategoriesAdapter(Context mContext) {
        this.mContext = mContext;
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

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        public CategoryViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.categoryItemText);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Fragment fragment = new EventFragment();
            ExtraParameters.category = categories.get(getAdapterPosition()).getSlug();
            ExtraParameters.fullCategory = categories.get(getAdapterPosition()).getName();
            ((MainActivity)mContext).getSupportFragmentManager().beginTransaction().
                    replace(R.id.container_fragment, fragment,"Category")
                    .addToBackStack(null)
                    .commit();
        }
    }
}
