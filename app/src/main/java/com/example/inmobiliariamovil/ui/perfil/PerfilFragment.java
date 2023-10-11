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
    private Propietario usuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if(propietario == null){
                    Log.d("error", "el usuario es nulo");
                }else {
                    usuario= propietario;
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
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setDni(Long.parseLong(binding.etDni.getText().toString()));
                usuario.setNombre(binding.etNombre.getText().toString());
                usuario.setApellido(binding.etApellido.getText().toString());
                usuario.setTelefono(binding.etTelefono.getText().toString());
                String texto= binding.btEditar.getText().toString();
                if (texto!= null){
                    perfilViewModel.accionBoton(texto,usuario);
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