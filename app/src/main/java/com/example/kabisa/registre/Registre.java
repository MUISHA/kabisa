package com.example.kabisa.registre;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registre extends Fragment  {
    TextView login,_btn_registre;
    private EditText _name_input,_email_input,_password_input,_reenterpassword_input;
    private ProgressBar _progressBar;
    public void Fragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.registre_,container,false);

        _name_input = view.findViewById(R.id._name_input);
        _progressBar = view.findViewById(R.id.progressBar_registre);
        _email_input = view.findViewById(R.id._email_input);
        _password_input = view.findViewById(R.id._password_input);
        _reenterpassword_input = view.findViewById(R.id._reenterpassword_input);

        _btn_registre = (TextView)view.findViewById(R.id.btn_registre);
        _btn_registre.setOnClickListener(v->{
            String name = _name_input.getText().toString().trim();
            String email = _email_input.getText().toString().trim();
            String password = _password_input.getText().toString().trim();
            String reepassword = _reenterpassword_input.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                _email_input.setError(getString(R.string._encarat_mail));
                _email_input.setFocusable(true);
            }else if (password.length()<8){
                _password_input.setError(getString(R.string._inferieur));
                _password_input.setFocusable(true);
            }else {
                /**********************Code registre ici ***************************/
                performUserUp();
            }

        });
        login = (TextView)view.findViewById(R.id._login);
        login.setOnClickListener(v -> {
            Login _login = new Login();
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container,_login);
            transaction.commit();
        });
        return view;

    }
    private void performUserUp(){
        String username = _name_input.getText().toString().trim();
        String  email=  _email_input.getText().toString().trim();
        String password = _password_input.getText().toString().trim();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).perfomUserSignIn(username,email,password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals(getString(R.string._oky))){
                        if (response.body().getResultCode()==1){
                            Toast.makeText(getContext(), getString(R.string.enregistre), Toast.LENGTH_SHORT).show();
                        }else {
                            displayUserInfo(getString(R.string._quel));
                            _password_input.setText("");
                        }
                    }else {
                        displayUserInfo(getString(R.string._quel));
                        _password_input.setText("");
                    }
                }else {
                    displayUserInfo(getString(R.string._quel));
                    _password_input.setText("");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getActivity(), R.string._error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displayUserInfo(String message){
        _password_input.setText("");
        _progressBar.setVisibility(View.INVISIBLE);
    }
}
