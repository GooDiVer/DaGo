package e.mi.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.List;

import e.mi.myapplication.Adapters.CitiesAdapter;
import e.mi.myapplication.Adapters.EventsAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Fragments.CitiesFragment;
import e.mi.myapplication.Fragments.EventFragment;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainInterface.intractor.onLoadDataListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle barToggle;

    Events events;
    List<City> cities;
    EventsAdapter eventsAdapter;
    CitiesAdapter citiesAdapter;
    DataLoader dataLoader;
    int counter = 0;
//    EventsAdapter eventsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLoader = new DataLoader();

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //Инициализируем выпадающее меню
        drawerLayout = findViewById(R.id.drawerLayout);
        barToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        //Устанавливаем кнопку
        drawerLayout.addDrawerListener(barToggle);
        barToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(MainActivity.this, "Events Clicked",Toast.LENGTH_LONG);
        int itemId = menuItem.getItemId();
        Fragment fragment;

        switch (itemId) {
            case R.id.eventsItem:
                //Здесь происходит вызов метода onLoadEventFinished, в котором events устанавливается значение
                dataLoader.loadData(this,0);
                //однако здесь events снова = null
                fragment = new EventFragment();
                Log.i("Callback","Method1 which call method2");

                break;
            case R.id.cityItem:
                fragment = new CitiesFragment();
                break;
            default:
                    fragment = new EventFragment();
        }

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.container_fragment,fragment)
                .addToBackStack(null)
                .commit();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(barToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadCategoriesFinished(List<Category> categories) {

    }

    @Override
    public void onLoadCitiesFinished(List<City> cities) {

        citiesAdapter.clear();
        citiesAdapter.addAll(cities);
        citiesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadEventFinished(Events events) {
        //MAKE CHECKING EVENTSADAPTER ON NULL
//        eventsAdapter = new EventsAdapter(this);
//        eventsAdapter.clear();
//        eventsAdapter.addAll(events);
//        eventsAdapter.notifyDataSetChanged();

        this.events = events;
        Log.i("Callback","Method2 which set this.events");

    }

    @Override
    public Events getEvents() {
        return events;
    }


    @Override
    public CitiesAdapter getCitiesAdapter() {

        return citiesAdapter;
    }

//    @Override
//    public EventsAdapter getCategoriesAdapter() {
//        return null;
//    }
}
