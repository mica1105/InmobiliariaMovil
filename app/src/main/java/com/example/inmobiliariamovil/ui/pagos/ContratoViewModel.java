package com.example.inmobiliariamovil.ui.pagos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.inmobiliariamovil.R;
import com.example.inmobiliariamovil.modelo.Contrato;
import com.example.inmobiliariamovil.modelo.Inmueble;
import com.example.inmobiliariamovil.request.ApiClient;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Contrato contratoActual;
    private Context context;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<Contrato> getContrato() {
        if (contrato== null){
            contrato= new MutableLiveData<>();
        }
        return contrato;
    }

    public void cargarContrato(Bundle bundle){
        Inmueble inmueble =(Inmueble) bundle.getSerializable("inmueble1");
        ApiClient apiClient= ApiClient.getApi();
        contratoActual= apiClient.obtenerContratoVigente(inmueble);
        contrato.setValue(contratoActual);
    }

    public void verPagos(){
        Bundle bundle= new Bundle();
        bundle.putSerializable("contrato",contratoActual);
        Navigation.findNavController((Activity) context,R.id.nav_host_fragment_content_main).navigate(R.id.pagosFragment, bundle);
    }
}