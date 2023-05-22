package com.example.inmobiliariamovil.ui.pagos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariamovil.R;
import com.example.inmobiliariamovil.modelo.Contrato;
import com.example.inmobiliariamovil.modelo.Pago;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater inflater;

    public PagosAdapter(Context context, List<Pago> pagos, LayoutInflater inflater) {
        this.context = context;
        this.pagos = pagos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_pago, parent, false);
        return new PagosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pago pago= pagos.get(position);
        holder.etCodigo.setText(pago.getIdPago());
        holder.etNroPago.setText(pago.getNumero());
        holder.etContrato.setText(pago.getContrato().getIdContrato());
        holder.etImporte.setText(pago.getImporte()+"");
        holder.etFecha.setText(pago.getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText etCodigo, etNroPago, etContrato, etImporte, etFecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etCodigo= itemView.findViewById(R.id.etCodigoPago);
            etNroPago= itemView.findViewById(R.id.etNroPago);
            etContrato= itemView.findViewById(R.id.etContrato);
            etImporte= itemView.findViewById(R.id.etImportePago);
            etFecha= itemView.findViewById(R.id.etFechaPago);

        }
    }
}
