package com.example.inmobiliariamovil.ui.contratos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.inmobiliariamovil.MainViewModel;
import com.example.inmobiliariamovil.R;
import com.example.inmobiliariamovil.modelo.Contrato;
import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.modelo.Pago;
import com.example.inmobiliariamovil.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Contrato contratoActual;
    private Context context;
    private ApiClient apiClient;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= ApiClient.getApi();
    }

    public LiveData<Contrato> getContrato() {
        if (contrato== null){
            contrato= new MutableLiveData<>();
        }
        return contrato;
    }


    public void cargarContrato(Bundle bundle){
        Inmueble inmueble =(Inmueble) bundle.getSerializable("inmueble1");
        contratoActual= apiClient.obtenerContratoVigente(inmueble);
        contrato.setValue(contratoActual);
    }

}