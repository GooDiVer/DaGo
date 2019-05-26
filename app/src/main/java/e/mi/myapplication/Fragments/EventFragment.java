package e.mi.myapplication.Fragments;

import android.content.Context;
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

import e.mi.myapplication.MainActivity;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.R;

public class EventFragment extends Fragment {

    MainInterface.intractor.onLoadDataListener dataListener;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_recycler, container, false);

        recyclerView = view.findViewById(R.id.events_items_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//
//        EventsAdapter eventsAdapter = new EventsAdapter(getActivity());
//        eventsAdapter.clear();
//        eventsAdapter.addAll(dataListener.getEvents());

//        recyclerView.setAdapter(eventsAdapter);

    }
}
