package com.example.inmobiliariamovil.ui.inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.request.ApiClient;

import java.util.ArrayList;

public class InquilinosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    private ApiClient api;
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        api= ApiClient.getApi();
    }
    public LiveData<ArrayList<Inmueble>> getInmuebles(){
        if (inmuebles== null){
            inmuebles= new MutableLiveData<>();
        }
        return inmuebles;
    }

    public  void cargarInmubles(){
        ArrayList<Inmueble> propiedades= api.obtenerPropiedadesAlquiladas();
        inmuebles.setValue(propiedades);
    }
}