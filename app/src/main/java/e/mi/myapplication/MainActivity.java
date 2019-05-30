package e.mi.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import java.util.List;

import e.mi.myapplication.Adapters.CitiesAdapter;
import e.mi.myapplication.Adapters.EventsAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Fragments.CategoriesFragment;
import e.mi.myapplication.Fragments.CitiesFragment;
import e.mi.myapplication.Fragments.EventFragment;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Events;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle barToggle;
    Toolbar toolbar;

    DataLoader dataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLoader = new DataLoader();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //Инициализируем выпадающее меню
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        //Устанавливаем кнопку
        barToggle.syncState();

//        drawerLayout.addDrawerListener(barToggle);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.toggleicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        makeFragment(new Fragment());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(MainActivity.this, "Events Clicked", Toast.LENGTH_LONG);
        int itemId = menuItem.getItemId();
        Fragment fragment;

        Log.i("MainActivity","before Error");

        switch (itemId) {

            case R.id.eventsItem:
                fragment = new EventFragment();
                ExtraParametrs.category = "";
                ExtraParametrs.city = "";
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

        makeFragment(fragment);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (barToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void makeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

}
