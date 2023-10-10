package com.example.inmobiliariamovil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.request.ApiClient;

public class MainViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Propietario> propietario;

    public MainViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= ApiClient.getApi();
    }

    public LiveData<Propietario> getPropietario(){
        if(propietario == null){
            propietario= new MutableLiveData<>();
        }
        return propietario;
    }

    public void obtenerUsuario(){
        propietario.setValue(apiClient.obtenerUsuarioActual());
    }
}
