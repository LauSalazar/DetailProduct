package com.example.u93.detailproduct.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.u93.detailproduct.R;
import com.example.u93.detailproduct.adapters.AdapterProducts;
import com.example.u93.detailproduct.helper.ValidateInternet;
import com.example.u93.detailproduct.models.Product;
import com.example.u93.detailproduct.services.Repository;
import com.example.u93.detailproduct.viewsinterface.IMainActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private RecyclerView recyclerView;
    private Repository repository;
    private AdapterProducts adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvRecyclerProducts);
        repository = new Repository();
        validateInternet();
    }

    private void validateInternet(){
        final ValidateInternet validateInternet = new ValidateInternet(this);
        if (validateInternet.verificarInternet()){
            createThreadCreateProducts();
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.error_internet);
            alertDialog.setMessage(R.string.message_error_internet);
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton(R.string.text_again, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    validateInternet();
                    dialogInterface.dismiss();
                }
            });
        }

    }

    private void getProducts() {
        try {
            ArrayList<Product> products = repository.getProducts();
            loadAdapterProducts(products);

        } catch (final IOException e) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void loadAdapterProducts(final ArrayList<Product> products) {
        if (this != null) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new AdapterProducts(products, MainActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }


    private void createThreadCreateProducts(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProducts();
            }
        });
        thread.start();
    }

    @Override
    public void intentToDetailActivity(Product producto) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
