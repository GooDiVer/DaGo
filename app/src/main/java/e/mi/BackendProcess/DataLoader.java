package e.mi.myapplication.BackendProcess;

import android.util.Log;

import e.mi.myapplication.R;

import java.util.List;

import e.mi.myapplication.Interfaces.KudaGoInterface;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataLoader implements MainInterface.intractor {

    onLoadDataListener listener;

    @Override
    public void loadData(final onLoadDataListener listener, int itemId) {
        this.listener = listener;

        KudaGoInterface goInterface = GetRetrofit.getRetrofit().create(KudaGoInterface.class);
        Call<Events> eventsCall;
        Call<List<City>> citiesCall;

        switch (itemId) {
            case R.id.eventsItem:
                eventsCall = goInterface.getEvent();
                handleEventCallback(eventsCall);
                break;
            case R.id.cityItem:
                citiesCall = goInterface.getCities("ru");
                handleCitiesCallback(citiesCall);
                break;

                default:
                    eventsCall = goInterface.getEvent();
                    handleEventCallback(eventsCall);
        }



    }

    public void handleEventCallback(Call<Events> eventsCall) {
        eventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                listener.onLoadEventFinished(response.body());
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.e("ServerLoadError", t.toString());
            }
        });
    }

    public void handleCitiesCallback(Call<List<City>> citiesCall) {
        citiesCall.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                listener.onLoadCitiesFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {

            }
        });
    }
}
