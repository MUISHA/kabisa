package com.example.kabisa.registre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.kabisa.R;
import com.example.kabisa.registre.modeles.ApiResponse;
import com.example.kabisa.registre.retrofitutil.ApiClient;
import com.example.kabisa.registre.retrofitutil.ApiInterface;
import com.example.kabisa.registre.retrofitutil.AppConfig;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Fragment {
    TextView _registre,_login;
    private EditText _etEmail_input,_etPassword_input;
    private CheckBox _checkBox;
    private ImageView img_gl,img_tw,img_lk,img_fb;
    ProgressBar _progressBar;
    private boolean isRememberUserLogin = false;
    private AppConfig appConfig;
    public void Fragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_,container,false);
        /*************Sign over count ***********************/

        img_gl = view.findViewById(R.id.img_gl);
        img_tw = view.findViewById(R.id.img_tw);
        img_lk = view.findViewById(R.id.img_lk);
        img_fb = view.findViewById(R.id.img_fb);
        _progressBar = view.findViewById(R.id.progress_circular);

        _etEmail_input = view.findViewById(R.id.etEmail_input);
        _etPassword_input = view.findViewById(R.id.etPassword_input);
        _login = view.findViewById(R.id.btn_login);
        appConfig = new AppConfig(getActivity());
        if (appConfig.isUserLogin()){
            String email = appConfig.getNaneOfUser();
            Intent intent = new Intent(getContext(), Menu.class);
            intent.putExtra("email",email);
            //finich();
        }
        _checkBox = view.findViewById(R.id.cheking_user);
        _checkBox.setOnClickListener(v -> {
            isRememberUserLogin = ((CheckBox)view).isChecked();
        });
        _login.setOnClickListener(v -> {
            _progressBar.setVisibility(View.VISIBLE);
            String email = _etEmail_input.getText().toString().trim();
            String pass = _etPassword_input.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                _etEmail_input.setError(getString(R.string._encarat_mail));
                _etEmail_input.setFocusable(true);
            }else if(pass.length()<8){
                _etPassword_input.setError(getString(R.string._inferieur));
                _etPassword_input.setFocusable(true);
            }else {
                /*********************Test code login sign In******************************/
                perfomUserLogin();
                _progressBar.setVisibility(View.VISIBLE);
            }
        });
        /**********Registre*********/
        _registre = (TextView)view.findViewById(R.id.txt_regisre);
        _registre.setOnClickListener(v -> {
            Registre registre = new Registre();
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container,registre);
            transaction.commit();
        });
        img_gl.setOnClickListener(v-> {
            Toast.makeText(getActivity(), "Google", Toast.LENGTH_SHORT).show();
        });
        img_tw.setOnClickListener(v-> {
            Toast.makeText(getActivity(), "Twitter", Toast.LENGTH_SHORT).show();
        });
        img_lk.setOnClickListener(v-> {
            Toast.makeText(getActivity(), "LinkendIn", Toast.LENGTH_SHORT).show();
        });
        img_fb.setOnClickListener(v-> {
            Toast.makeText(getActivity(), "Facebook", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
    private void perfomUserLogin(){
        String email = _etEmail_input.getText().toString().trim();
        String password = _etPassword_input.getText().toString().trim();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).perfomUserLogin(email,password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code()==200){
                    if (response.body().getStatus().equals("OK"))
                    {
                        if (response.body().getResultCode()==1){
                            String email = response.body().getName();
                            if (isRememberUserLogin){
                                appConfig.updateUserLginStatus(true);
                                appConfig.savaNameOfUser(email);
                            }
                            Intent intent = new Intent(getActivity(), Menu.class);
                            intent.putExtra("email",email);
                            startActivity(intent);

                        }else {
                            displayUserInforation("Pas d'acces");
                            _etPassword_input.setText("");
                        }
                    }else {
                        displayUserInforation("Something went wrong...");
                        _etPassword_input.setText("");
                    }
                }else {
                        displayUserInforation("Something went wrong...");
                        _etPassword_input.setText("");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
    private void displayUserInforation(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        _progressBar.setVisibility(View.INVISIBLE);
    }
}
