package com.example.inmobiliariamovil.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private ApiClient apiClient;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= ApiClient.getApi();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles== null){
            inmuebles= new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmuebles(){
        ArrayList<Inmueble> misContratos= apiClient.obtenerPropiedadesAlquiladas();
        inmuebles.setValue(misContratos);
    }
}