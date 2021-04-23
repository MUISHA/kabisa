package com.example.kabisa.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kabisa.R;
import com.example.kabisa.registre.Login;

import static android.content.Intent.getIntent;

public class MenuFragment extends Fragment {
    static String name;
    private TextView textView;
    Login login;
    public MenuFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.menu_, container, false);
        textView = view.findViewById(R.id.hello);

        //name = view.getContext().getStringExtra("email");
        textView.setText("" + name);
        return view;
    }
}
