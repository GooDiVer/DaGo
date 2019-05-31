package e.mi.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import e.mi.myapplication.ExtraParameters;
import e.mi.myapplication.Interfaces.MainInterface;
import e.mi.myapplication.R;

public class PersonalAreaFragment extends Fragment {
    TextView city;
    TextView name;
    ImageView avatar;
    Button button;
    TextView email;

    MainInterface.fragmentItemListener fragmentItemListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_area,container,false);
        avatar = view.findViewById(R.id.avatarImageView);
        city = view.findViewById(R.id.cityName);
        name = view.findViewById(R.id.personName);
        button = view.findViewById(R.id.authButton);
        email = view.findViewById(R.id.emailPlain);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        avatar.setImageResource(R.mipmap.personavatar);
        city.setText(ExtraParameters.fullCityName);
        Toast.makeText(getActivity(), ExtraParameters.personName, Toast.LENGTH_LONG).show();
        name.setText(ExtraParameters.personName);


    }
}
