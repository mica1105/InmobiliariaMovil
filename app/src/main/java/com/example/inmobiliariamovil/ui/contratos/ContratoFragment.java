package com.example.inmobiliariamovil.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariamovil.R;
import com.example.inmobiliariamovil.databinding.FragmentContratoBinding;
import com.example.inmobiliariamovil.databinding.FragmentContratosBinding;
import com.example.inmobiliariamovil.modelo.Contrato;
import com.example.inmobiliariamovil.modelo.Inmueble;

public class ContratoFragment extends Fragment {

    private ContratoViewModel mViewModel;
    private FragmentContratoBinding binding;
    private Contrato contratoActual;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= FragmentContratoBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                contratoActual= contrato;
                binding.tvCodigoContrato.setText(contrato.getIdContrato()+"");
                binding.tvFechaI.setText(contrato.getFechaInicio()+"");
                binding.tvFechaF.setText(contrato.getFechaFin()+"");
                binding.tvInmueble.setText("Inmueble en "+contrato.getInmueble().getDireccion());
                binding.tvInquilino.setText(contrato.getInquilino().getNombre()+ " "+ contrato.getInquilino().getApellido());
                binding.tvMonto.setText("$"+contrato.getMontoAlquiler());
            }
        });
        mViewModel.cargarContrato(getArguments());
        binding.btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("contrato", contratoActual);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main)
                        .navigate(R.id.pagosFragment, bundle);;
            }
        });
    }

}