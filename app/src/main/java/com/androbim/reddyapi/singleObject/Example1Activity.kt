package com.androbim.reddyapi.singleObject

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androbim.reddyapi.api.ApiInterface
import com.androbim.reddyapi.databinding.ActivityExample1Binding
import com.androbim.reddyapi.utilites.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Example1Activity : AppCompatActivity() {
    lateinit var binding: ActivityExample1Binding
    lateinit var progressDialog: ProgressDialog
    lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExample1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog=ProgressDialog(this)
        progressDialog.setCancelable(false)

        apiInterface=RetrofitInstance.getInstance().create(ApiInterface::class.java)

        binding.SubmitBtn.setOnClickListener {

            progressDialog.show()
            progressDialog.setMessage("Loading Data")

            val myData = apiInterface.getData1()
            myData.enqueue(object :Callback<SingleModel>{
                override fun onResponse(call: Call<SingleModel>, response: Response<SingleModel>) {
                    //TODO("Not yet implemented")
                    progressDialog.dismiss()
                    if(response.isSuccessful)
                    {
                        val id=response.body()!!.id
                        val userID=response.body()!!.userId
                        val title=response.body()!!.title
                        val body=response.body()!!.title

                        binding.responseTxt.text="$id \n $userID \n $title \n $body"

                    }
                    else{

                        Toast.makeText(this@Example1Activity, ""+response.body(), Toast.LENGTH_SHORT).show();

                    }
                }

                override fun onFailure(call: Call<SingleModel>, t: Throwable) {
                    //TODO("Not yet implemented")
                    progressDialog.dismiss()
                    Toast.makeText(this@Example1Activity, ""+t.localizedMessage, Toast.LENGTH_SHORT).show();

                }

            })
        }
    }
}