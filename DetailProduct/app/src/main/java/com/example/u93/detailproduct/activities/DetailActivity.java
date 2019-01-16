package com.example.u93.detailproduct.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.u93.detailproduct.R;
import com.example.u93.detailproduct.models.Product;

public class DetailActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        product = (Product) getIntent().getSerializableExtra("producto");
    }
}
