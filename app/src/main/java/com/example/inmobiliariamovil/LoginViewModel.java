package com.example.inmobiliariamovil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData error;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<String> getError() {
        if (error== null){
            error= new MutableLiveData<>();
        }
        return error;
    }

    public void autenticar(String usuario, String password){
        Propietario propietario= ApiClient.getApi().login(usuario,password);
        if (propietario != null){
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //i.putExtra("usuario", propietario);
            context.startActivity(i);
        } else {
            error.setValue("*El email o password son incorrectos");
        }
    }

}
