package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.text.HtmlCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import e.mi.myapplication.Adapters.ImagesPagerAdapter;
import e.mi.myapplication.BackendProcess.DataLoader;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.MainActivity;
import e.mi.myapplication.Net.Category;
import e.mi.myapplication.Net.City;
import e.mi.myapplication.Net.Event.Image;
import e.mi.myapplication.Net.Event.Result;
import e.mi.myapplication.Net.Events;
import e.mi.myapplication.R;

public class DetailFragment extends Fragment {

    MainInterface.intractor.onLoadDataListener dataListener;

    ViewPager viewPager;
    TextView bodyText;
    TextView descText;
    ArrayList<String> imageList;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);

        viewPager = view.findViewById(R.id.detail_view_pager);

        imageList = new ArrayList<>();

        tabLayout = view.findViewById(R.id.TabDots);

        bodyText = view.findViewById(R.id.bodyTextView);
        descText = view.findViewById(R.id.descriptionTextView);

        return  view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dataListener = new MainInterface.intractor.onLoadDataListener() {
            @Override
            public void onLoadEventFinished(Events events) {

            }

            @Override
            public void onLoadOneEventFinished(Result result) {


                List<Image> images = result.getImages();

                for(Image x : images) {
                    imageList.add(x.getImage());
                }

                ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(getActivity());
                pagerAdapter.setImageList(imageList);

                descText.setText(HtmlCompat.fromHtml(result.getDescription(),0));
                bodyText.setText(HtmlCompat.fromHtml(result.getBodyText(),0));
                bodyText.setMovementMethod(LinkMovementMethod.getInstance());
                viewPager.setAdapter(pagerAdapter);
                tabLayout.setupWithViewPager(viewPager,true);

            }

            @Override
            public void onLoadCitiesFinished(List<City> cities) {

            }

            @Override
            public void onLoadCategoriesFinished(List<Category> categories) {

            }
        };

        DataLoader dataLoader = new DataLoader();
        int id = (int)getArguments().get("item id");

        dataLoader.setListener(dataListener);
        dataLoader.onLoadDetailedItemListener(id);

    }
}
