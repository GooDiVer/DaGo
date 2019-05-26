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
import e.mi.myapplication.Fragments.CategoriesFragment;
import e.mi.myapplication.Fragments.CitiesFragment;
import e.mi.myapplication.Fragments.EventFragment;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


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

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //Инициализируем выпадающее меню
        drawerLayout = findViewById(R.id.drawerLayout);
        barToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        //Устанавливаем кнопку
        drawerLayout.addDrawerListener(barToggle);
        barToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(MainActivity.this, "Events Clicked", Toast.LENGTH_LONG);
        int itemId = menuItem.getItemId();
        Fragment fragment;

        switch (itemId) {
            case R.id.eventsItem:
                fragment = new EventFragment();
                break;
            case R.id.cityItem:
                fragment = new CitiesFragment();
                break;
            case R.id.categoryItem:
                fragment = new CategoriesFragment();
                break;
            default:
                fragment = new EventFragment();
        }

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.container_fragment, fragment)
                .addToBackStack(null)
                .commit();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (barToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
