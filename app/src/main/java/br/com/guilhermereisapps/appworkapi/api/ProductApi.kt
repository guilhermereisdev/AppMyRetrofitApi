package br.com.guilhermereisapps.appworkapi.api

import br.com.guilhermereisapps.appworkapi.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {

    @GET("getdata.php")
    fun getProductApi(): Call<List<Product>>

}