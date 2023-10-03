package com.example.inmobiliariamovil.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.modelo.Inquilino;
import com.example.inmobiliariamovil.request.ApiClient;

public class InquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Inquilino> inquilino;
    private ApiClient api;
    private Context context;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        api= ApiClient.getApi();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilino==null){
            inquilino= new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle){
        Inmueble inmueble= (Inmueble) bundle.getSerializable("inmueble");
        Inquilino inqui= api.obtenerInquilino(inmueble);
        inquilino.setValue(inqui);
    }
}