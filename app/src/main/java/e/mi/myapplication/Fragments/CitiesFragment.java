package e.mi.myapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import e.mi.myapplication.Adapters.CitiesAdapter;
import e.mi.myapplication.myapplication.MainActivity;

import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.R;

public class CitiesFragment extends Fragment {
    MainInterface.intractor.onLoadDataListener dataListener;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_recycler, container, false);

        Log.i("Fragment", "you are in the city");

        recyclerView = view.findViewById(R.id.city_items_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DataLoader dataLoader = new DataLoader();
//        dataLoader.loadData(dataListener,R.id.cityItem);

        CitiesAdapter citiesAdapter = dataListener.getCitiesAdapter();

        recyclerView.setAdapter(citiesAdapter);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            dataListener = (MainInterface.intractor.onLoadDataListener)getActivity();
        }
        else {
            Log.i("Error","This is not fragment of needed interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
