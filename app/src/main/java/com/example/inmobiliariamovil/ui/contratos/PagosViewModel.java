package com.example.inmobiliariamovil.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariamovil.modelo.Contrato;
import com.example.inmobiliariamovil.modelo.Pago;
import com.example.inmobiliariamovil.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> pagos;
    private ApiClient api;
    public PagosViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        api= ApiClient.getApi();
    }

    public LiveData<List<Pago>> getPagos() {
        if(pagos == null){
            pagos= new MutableLiveData<>();
        }
        return pagos;
    }

    public void cargarPagos(Bundle bundle){
        Contrato contrato= (Contrato) bundle.getSerializable("contrato");
        ArrayList<Pago> misPagos= api.obtenerPagos(contrato);
        pagos.setValue(misPagos);
    }
}