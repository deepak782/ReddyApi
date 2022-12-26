package com.androbim.reddyapi.gorest.activites

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.reddyapi.R
import com.androbim.reddyapi.databinding.ActivityCreateUserBinding
import com.androbim.reddyapi.gorest.gorestApi.GorestApi
import com.androbim.reddyapi.gorest.gorestResponse.GorestList
import com.androbim.reddyapi.gorest.gorestResponse.UserParameters
import com.androbim.reddyapi.gorest.gorestResponse.UserResponse
import com.androbim.reddyapi.gorest.goretRetrofitInstance.GorestInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserActivity : AppCompatActivity() {
    lateinit var  binding: ActivityCreateUserBinding
    lateinit var gorestApi: GorestApi
    lateinit var progressDialog: ProgressDialog
    lateinit var nameString: String
    lateinit var mailString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gorestApi=GorestInstance.getInstance().create(GorestApi::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)


        binding.createUser.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("Creating User!!")


            nameString=binding.edtName1.text.toString()
            mailString=binding.edtMail1.text.toString()

            val params=UserParameters(nameString,mailString,"male","active")


            val call=gorestApi.goRestCreate(params)

            call.enqueue(object :Callback<UserResponse>
            {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    progressDialog.dismiss()

                    Toast.makeText(applicationContext, ""+response.body(), Toast.LENGTH_SHORT).show();
                    when(response.code())
                    {
                        200->{

                            val id=response.body()!!.id
                            val name=response.body()!!.params.name
                            Toast.makeText(applicationContext, "$id\n$name", Toast.LENGTH_SHORT).show();
                        }
                        201-> Log.d("TAG", "onResponse: 201")
                        204-> Log.d("TAG", "onResponse: 204")
                        304-> Log.d("TAG", "onResponse: 304")
                        400-> Log.d("TAG", "onResponse: 400")
                        401-> Log.d("TAG", "onResponse: 401")
                        403-> Log.d("TAG", "onResponse: 403")
                        404-> Log.d("TAG", "onResponse: 404")
                        405-> Log.d("TAG", "onResponse: 405")
                        415-> Log.d("TAG", "onResponse: 415")
                        422-> Log.d("TAG", "onResponse: 422")
                        429-> Log.d("TAG", "onResponse: 429")
                        500-> Log.d("TAG", "onResponse: 500")
                    }

                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }

    }
}