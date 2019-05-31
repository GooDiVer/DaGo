package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import e.mi.myapplication.Adapters.CitiesAdapter;

import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.ExtraParameters;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Event.Result;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

public class CitiesFragment extends Fragment {
    MainInterface.intractor.onLoadDataListener dataListener;
    RecyclerView recyclerView;
    CitiesAdapter citiesAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);

        DataLoader dataLoader = new DataLoader();

        citiesAdapter = new CitiesAdapter(getActivity());

        recyclerView = view.findViewById(R.id.city_items_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        citiesAdapter.setOnItemClickListener(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Fragment fragment = new EventFragment();

                ExtraParameters.city = citiesAdapter.getCity(position).getSlug();
                ExtraParameters.fullCityName = citiesAdapter.getCity(position).getName();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment,fragment,"Okay")
                        .addToBackStack(null)
                        .commit();
            }
        });

        dataListener = new MainInterface.intractor.onLoadDataListener() {
            @Override
            public void onLoadEventFinished(Events events) {
            }

            @Override
            public void onLoadOneEventFinished(Result result) {

            }

            @Override
            public void onLoadCitiesFinished(List<City> cities) {
                citiesAdapter.addAll(cities);
                citiesAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(citiesAdapter);
            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {

            }
        };

        dataLoader.setListener(dataListener);
        dataLoader.loadData(R.id.cityItem);

        return view;

    }



}
