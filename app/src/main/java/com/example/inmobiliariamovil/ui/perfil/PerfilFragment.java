package com.example.inmobiliariamovil.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.inmobiliariamovil.LoginActivity;
import com.example.inmobiliariamovil.databinding.FragmentPerfilBinding;
import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.ui.ubicacion.UbicacionViewModel;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar(root);
        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if(propietario == null){
                    Log.d("error", "el usuario es nulo");
                }else {
                    binding.etCodigo.setText(propietario.getId() + "");
                    binding.etDni.setText(propietario.getDni() + "");
                    binding.etApellido.setText(propietario.getApellido());
                    binding.etNombre.setText(propietario.getNombre());
                    binding.etEmail.setText(propietario.getEmail());
                    binding.etPassword.setText(propietario.getContraseña());
                    binding.etTelefono.setText(propietario.getTelefono());
                    binding.ivFotoPerfil.setImageResource(propietario.getAvatar());
                }
            }
        });
        perfilViewModel.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDni.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etEmail.setEnabled(aBoolean);
                binding.etPassword.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });
        perfilViewModel.getTextoBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btEditar.setText(s);
            }
        });
        perfilViewModel.cargarUsuario();
        return root;
    }

    private void inicializar(View root) {

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario= new Propietario();
                propietario.setDni(Long.parseLong(binding.etDni.getText().toString()));
                propietario.setNombre(binding.etNombre.getText().toString());
                propietario.setApellido(binding.etApellido.getText().toString());
                propietario.setEmail(binding.etEmail.getText().toString());
                propietario.setContraseña(binding.etPassword.getText().toString());
                propietario.setTelefono(binding.etTelefono.getText().toString());
                String texto= binding.btEditar.getText().toString();
                if (texto!= null){
                    perfilViewModel.accionBoton(texto,propietario);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}