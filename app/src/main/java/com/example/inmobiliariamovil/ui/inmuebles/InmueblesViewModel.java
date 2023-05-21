package com.example.inmobiliariamovil.ui.inmuebles;

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

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    private ApiClient api;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        api= ApiClient.getApi();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles(){
        if (inmuebles==null){
            inmuebles= new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmuebles(){
        ArrayList<Inmueble> propiedades= api.obtnerPropiedades();
        inmuebles.setValue(propiedades);
    }

}