package com.example.inmobiliariamovil.ui.perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Propietario> propietario;
    private ApiClient apiClient;

    public PerfilViewModel() {
        apiClient = ApiClient.getApi();
    }


    public LiveData<Propietario> getPropietario() {
        if (propietario == null){
            propietario= new MutableLiveData<>();
        }
        return propietario;
    }
    public void cargarPropietario(){
        apiClient.obtenerUsuarioActual();
    }
}