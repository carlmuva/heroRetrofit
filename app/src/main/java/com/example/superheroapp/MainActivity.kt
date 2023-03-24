package com.example.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.superheroapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit=getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty()) // Si es null regresara un texto vacio
                return  false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        adapter = SuperheroAdapter{superheroId-> navigateToDetail(superheroId)}
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter=adapter

    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible=true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if(myResponse.isSuccessful){
                Log.i("carlos", "Funciona :)")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response!=null){
                    Log.i("carlos",response.toString())
                    runOnUiThread {
                        adapter.updateList(response.results)
                        binding.progressBar.isVisible = false
                    }

                }
            }else{
                Log.i("carlos","No funciona :(")
            }
        }

    }

    private fun getRetrofit(): Retrofit{ // Objeto retrofit
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)

    }
}