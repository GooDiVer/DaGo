package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import e.mi.myapplication.Adapters.CitiesAdapter;

import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.ExtraParametrs;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

import static android.widget.LinearLayout.VERTICAL;

public class CitiesFragment extends Fragment {
    MainInterface.intractor.onLoadDataListener dataListener;
    RecyclerView recyclerView;
    CitiesAdapter citiesAdapter;

    private final String EXTRA_PARAMETR_CITY = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);

        Bundle bundle = getArguments();

        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), VERTICAL);

        DataLoader dataLoader = new DataLoader();

        citiesAdapter = new CitiesAdapter(getActivity());

        recyclerView = view.findViewById(R.id.city_items_recycler_view);

        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        citiesAdapter.setOnItemClickListener(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Fragment fragment = new EventFragment();
                ExtraParametrs.city = citiesAdapter.getCity(position).getSlug();
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
            public void onLoadCitiesFinished(List<City> cities) {
                citiesAdapter.addAll(cities);
                citiesAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(citiesAdapter);
            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {

            }
        };

        dataLoader.loadData(dataListener,R.id.cityItem);

        return view;

    }



}
