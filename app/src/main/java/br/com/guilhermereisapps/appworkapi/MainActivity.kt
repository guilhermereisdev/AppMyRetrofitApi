package br.com.guilhermereisapps.appworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.guilhermereisapps.appworkapi.api.MyRetrofit
import br.com.guilhermereisapps.appworkapi.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerProducts = findViewById(R.id.recycler_products)
        recyclerProducts.layoutManager = LinearLayoutManager(this)

        getData()

    }

    private fun getData() {
        val call: Call<List<Product>> =
            MyRetrofit.instance?.productApi()?.getProductApi() as Call<List<Product>>
        call.enqueue(object : Callback<List<Product>> {
            // tratamento quando há êxito
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val adapter =
                    ProductAdapter(this@MainActivity, response.body()?.toList() as List<Product>)
                recyclerProducts.adapter = adapter
            }

            // tratamento quando há falha
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

}