package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import e.mi.myapplication.Adapters.CategoriesAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

public class CategoriesFragment extends Fragment {
    MainInterface.intractor.onLoadDataListener dataListener;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_categories, container, false);

        DataLoader dataLoader = new DataLoader();

        recyclerView = view.findViewById(R.id.category_items_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataListener = new MainInterface.intractor.onLoadDataListener() {
            @Override
            public void onLoadEventFinished(Events events) {
            }

            @Override
            public void onLoadCitiesFinished(List<City> Categories) {

            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {
                Log.i("Category", "Haha ");

                CategoriesAdapter categoriesAdapter = new CategoriesAdapter();

                categoriesAdapter.addAll(categories);
                categoriesAdapter.notifyDataSetChanged();

                recyclerView.setAdapter(categoriesAdapter);
            }
        };

        dataLoader.loadData(dataListener, R.id.categoryItem);

        return view;

    }

}
