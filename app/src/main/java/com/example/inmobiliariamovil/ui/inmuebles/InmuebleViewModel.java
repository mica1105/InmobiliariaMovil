package com.example.inmobiliariamovil.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Inmueble;

public class InmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmueble;

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
}