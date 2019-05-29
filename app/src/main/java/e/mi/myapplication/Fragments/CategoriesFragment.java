package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import e.mi.myapplication.Adapters.CategoriesAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Event.Result;
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

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        dataListener = new MainInterface.intractor.onLoadDataListener() {
            @Override
            public void onLoadEventFinished(Events events) {
            }

            @Override
            public void onLoadOneEventFinished(Result result) {

            }

            @Override
            public void onLoadCitiesFinished(List<City> Categories) {

            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {

                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity());

                categoriesAdapter.addAll(categories);
                categoriesAdapter.notifyDataSetChanged();

                recyclerView.setAdapter(categoriesAdapter);
            }
        };

        dataLoader.setListener(dataListener);
        dataLoader.loadData(R.id.categoryItem);

        return view;

    }

}
