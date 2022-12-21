package com.androbim.reddyapi.weather

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androbim.reddyapi.R
import com.androbim.reddyapi.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWeatherBinding
    lateinit var weatherApiInterface: WeatherApiInterface
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        weatherApiInterface=WeatherRetrofitInstance.getInstance().create(WeatherApiInterface::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)

        binding.weatherBtn.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("loading weather!!")

            val weatherData=weatherApiInterface.getWeatherReport("Delhi","a847de79ed9e5152022f478c2675fdf2")

            weatherData.enqueue(object :Callback<WeatherModel>{
                override fun onResponse(
                    call: Call<WeatherModel>,
                    response: Response<WeatherModel>
                ) {
                    progressDialog.dismiss()
                    val weatherModel:WeatherModel=response.body()!!
                    val base=weatherModel.base
                    val coord=weatherModel.coord
                    val lat=coord.lat
                    val lon=coord.lon
                    val cityname=weatherModel.name

                    binding.weatherTxt.text="$base\n$lat\n$lon\n$cityname"
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }
    }
}