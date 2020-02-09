package com.kobra.wegmanswear.network;

import com.kobra.wegmanswear.dataobjects.ShoppingList;
import com.kobra.wegmanswear.dataobjects.ShoppingListMeta;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("endpoints/lists")
    Single<Response<ArrayList<ShoppingListMeta>>> getShoppingLists(@Header("x-api-key")Integer id);

    @GET("endpoints/lists/{id}")
    Single<Response<ShoppingList>> getShoppingList(@Path("id") int listId,
                                                   @Header("x-api-key")Integer id);

    @PUT("endpoints/lists/{id}/{pid}?checked={checked}")
    Single<Response<String>> updateProduct(@Path("id") int listId,
                                                   @Path("pid") int productId,
                                                   @Path("checked") boolean checked);



}
