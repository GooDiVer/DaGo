package e.mi.myapplication.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.mi.myapplication.Net.City;
import e.mi.myapplication.R;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {
    List<City> cities;
    Context context;
    OnItemClickListener listener;

    public City getCity(int i ) {
        return cities.get(i);
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void clear(){
        if(cities != null)
            cities.clear();
    }
    public void addAll(List<City> cities) {
        this.cities = cities;
    }


    public CitiesAdapter(Context context) {
        this.context = context;
    }
    public CitiesAdapter() {
    }

    @NonNull
    @Override
    public CitiesAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_city, viewGroup,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesAdapter.CityViewHolder cityViewHolder, int i) {
        cityViewHolder.text.setText(cities.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public CityViewHolder(final View view) {
            super(view);

            text = view.findViewById(R.id.cityItemText);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(view,getAdapterPosition());
                }
            });
        }

    }
}
