package e.mi.myapplication.Interfaces;



import java.util.List;

import e.mi.myapplication.Net.*;
import e.mi.myapplication.Net.Event.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KudaGoInterface {
    @GET("events/{id}/")
    Call<Result> getResult(@Path("id") int resultId);

    @GET("events/?expand=place,location,dates,participants,images,title&fields=id,place,location,dates,participants,images,title" +
            "&order_by=-publication_date")
    Call<Events> getEvents(@Query("categories") String category, @Query("location") String location);

    @GET("event-categories/?")
    Call<List<Category>> getCategories(@Query("lang") String lang);

    @GET("locations/?")
    Call<List<City>> getCities(@Query("lang") String lang);

    @GET("locations/{city}/")
    Call<CityInfo> getCityInfo(@Path("city") String city);
}
