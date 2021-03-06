package e.mi.myapplication.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import e.mi.myapplication.Fragments.DetailFragment;
import e.mi.myapplication.MainActivity;
import e.mi.myapplication.Net.Event.Result;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    List<Result> list;
    Context mContext;

    public EventsAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public EventsAdapter() {
    }

    public void clear() {
        if(list != null)
            list.clear();
    }

    public void addAll(Events events) {
        list = events.getResults();
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.EventViewHolder eventViewHolder, int i) {
        Result result = list.get(i);

        eventViewHolder.id = result.getId();
        eventViewHolder.title.setText(result.getTitle());
//        eventViewHolder.date.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date(result.getDates().get(0).getStartDate()*1000)));
        eventViewHolder.date.setText(result.getDates().get(result.getDates().size() - 1).getStartDate());
        eventViewHolder.location.setText(result.getLocation().getName());
        Glide.with(mContext).load(result.getImages().get(0).getImage()).into(eventViewHolder.imageView);
    }

    @NonNull
    @Override
    public EventsAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_card_view_event,viewGroup,false);
        return new EventViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title;
        TextView date;
        TextView location;
        int id;

        public EventViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.cover_event);
            title = v.findViewById(R.id.title_event);
            date = v.findViewById(R.id.date_event);
            location = v.findViewById(R.id.location_event);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Fragment fragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("item id",id);
            fragment.setArguments(bundle);

            ((MainActivity)mContext).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment,fragment,"DetailFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}
