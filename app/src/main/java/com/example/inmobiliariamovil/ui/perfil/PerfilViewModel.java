package com.example.inmobiliariamovil.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> usuario;
    private MutableLiveData<Boolean> estado;
    private MutableLiveData<String> textoBoton;
    private Context context;
    private ApiClient apiClient;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= ApiClient.getApi();
    }


    public LiveData<Propietario> getUsuario() {
        if (usuario == null){
            usuario= new MutableLiveData<>();
        }
        return usuario;
    }
    public LiveData<Boolean> getEstado() {
        if (estado == null){
            estado= new MutableLiveData<>();
        }
        return estado;
    }

    public LiveData<String> getTextoBoton(){
        if (textoBoton== null){
            textoBoton= new MutableLiveData<>();
        }
        return textoBoton;
    }
    public void accionBoton(String textoB, Propietario propietario){
        if (textoB.equals("Editar")){
            textoBoton.setValue("Guardar");
            estado.setValue(true);
        }
        if (textoB.equals("Guardar")){
            apiClient.actualizarPerfil(propietario);
            textoBoton.setValue("Editar");
            estado.setValue(false);

        }
    }
    public void cargarUsuario(){
        usuario.setValue(apiClient.obtenerUsuarioActual());
    }
}