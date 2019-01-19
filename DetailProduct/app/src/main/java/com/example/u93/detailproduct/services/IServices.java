package com.example.u93.detailproduct.services;


import com.example.u93.detailproduct.models.DeleteResponse;
import com.example.u93.detailproduct.models.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IServices {

    @GET("products")
    Call<ArrayList<Product>> getProducts();

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<DeleteResponse> deleteProduct(@Path("_id") String id);
}
