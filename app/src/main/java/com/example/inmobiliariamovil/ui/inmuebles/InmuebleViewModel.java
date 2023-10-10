package com.example.inmobiliariamovil.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.request.ApiClient;

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private ApiClient apiClient;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        apiClient= ApiClient.getApi();
    }

    public LiveData<Inmueble> getInmueble(){
        if (inmueble== null){
            inmueble= new MutableLiveData<>();
        }
        return inmueble;
    }

    public void cargarInmueble(Bundle bundle){
        Inmueble propiedad= (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(propiedad);
    }

    public void editarDisponibilidad(Inmueble inmueble){
        apiClient.actualizarInmueble(inmueble);
    }
}