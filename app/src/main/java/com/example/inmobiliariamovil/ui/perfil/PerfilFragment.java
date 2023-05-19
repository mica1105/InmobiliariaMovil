package com.example.inmobiliariamovil.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.inmobiliariamovil.databinding.FragmentPerfilBinding;
import com.example.inmobiliariamovil.modelo.Propietario;
import com.example.inmobiliariamovil.ui.ubicacion.UbicacionViewModel;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar();

        return root;
    }

    private void inicializar() {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);;
        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etCodigo.setText(propietario.getId());
                binding.etDni.setText(propietario.getDni()+"");
                binding.etApellido.setText(propietario.getApellido());
                binding.etNombre.setText(propietario.getNombre());
                binding.etEmail.setText(propietario.getEmail());
                binding.etPassword.setText(propietario.getContraseña());
                binding.etTelefono.setText(propietario.getTelefono());
            }
        });

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btEditar.setVisibility(View.GONE);
                binding.btGuardar.setVisibility(View.VISIBLE);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}