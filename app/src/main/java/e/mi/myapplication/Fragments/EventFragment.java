package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import e.mi.myapplication.Adapters.EventsAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.ExtraParameters;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Event.Result;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

public class EventFragment extends Fragment {

    MainInterface.intractor.onLoadDataListener dataListener;
    public MainInterface.fragmentItemListener fragmentItemListener;

    RecyclerView recyclerView;

    public void setFragmentItemListener(MainInterface.fragmentItemListener fragmentItemListener) {
        this.fragmentItemListener = fragmentItemListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_events, container, false);

        recyclerView = view.findViewById(R.id.events_items_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentItemListener.changeToolbar(ExtraParameters.fullCityName + " " + ExtraParameters.fullCategory);

        DataLoader dataLoader = new DataLoader();

        dataListener = new MainInterface.intractor.onLoadDataListener() {
            @Override
            public void onLoadEventFinished(Events events) {
                if(events == null) {
                    Toast.makeText(getActivity(),"Таких событий не найдено :(", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),"Выберите другой город или категорию ;)", Toast.LENGTH_LONG).show();
                }

                EventsAdapter eventsAdapter = new EventsAdapter(getActivity());
                eventsAdapter.addAll(events);
                eventsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(eventsAdapter);

            }

            @Override
            public void onLoadOneEventFinished(Result result) {

            }

            @Override
            public void onLoadCitiesFinished(List<City> cities) {

            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {

            }
        };

        dataLoader.setListener(dataListener);
        dataLoader.loadData(R.id.eventsItem);

    }


}
