package com.example.u93.detailproduct.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u93.detailproduct.R;
import com.example.u93.detailproduct.helper.ValidateInternet;
import com.example.u93.detailproduct.models.Product;
import com.example.u93.detailproduct.services.Repository;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    private Product product;
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvMarca;
    private Repository repository;
    private ValidateInternet validateInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        repository = new Repository();
        validateInternet = new ValidateInternet(this);

        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvMarca = findViewById(R.id.tvMarca);

        product = (Product) getIntent().getSerializableExtra("producto");

        tvNombre.setText(product.getName());
        tvDescripcion.setText(product.getDescription());
        tvPrecio.setText(String.valueOf(product.getPrecio()));
        tvMarca.setText(product.getMarca());


    }

    public void eliminarProductoOnClick(View view) {
        if (validateInternet.verificarInternet()){
            createThreadDeleteProduct();
        }
    }


    private void createThreadDeleteProduct(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                deleteProduct();
            }
        });
        thread.start();
    }

    private boolean deleteProduct(){
        try {
            repository.deleteProduct(product.getId());
            showToast(getResources().getString(R.string.deleteProduct));
            finish();
        } catch (final IOException e) {
            showToast(e.getMessage());
        }
        return true;
    }

    private void showToast(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DetailActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

}
