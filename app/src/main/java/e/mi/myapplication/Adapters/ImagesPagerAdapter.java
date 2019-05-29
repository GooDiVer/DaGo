package e.mi.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.R;

public class ImagesPagerAdapter extends PagerAdapter {

    Context mContext;
    ArrayList<String> imageList;

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public ImagesPagerAdapter(Context context) {
        this.mContext = context;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_item,null);

        ImageView imageView = view.findViewById(R.id.pagerItemImageView);

        Glide.with(mContext).load(imageList.get(position)).into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        //return count of image strings
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o == view;
    }
}
