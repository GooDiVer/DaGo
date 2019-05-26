package e.mi.myapplication.BackendProcess;

import android.util.Log;

import e.mi.myapplication.Interfaces.KudaGoInterface;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

import java.util.List;

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
        Call<List<Category>> categoriesCall;

        switch (itemId) {
            case R.id.eventsItem:
                eventsCall = goInterface.getEvent();
                handleEventCallback(eventsCall);
                break;
            case R.id.cityItem:
                citiesCall = goInterface.getCities("ru");
                handleCitiesCallback(citiesCall);
                break;
            case R.id.categoryItem:
                categoriesCall = goInterface.getCategories("ru");
                handleCategoriesCallback(categoriesCall);
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

    public void handleCategoriesCallback(Call<List<Category>> categoriesCall) {
        categoriesCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                listener.onLoadCategoriesFinished(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
}
