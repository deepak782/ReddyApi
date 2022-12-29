package com.androbim.reddyapi.gorest.activites

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androbim.reddyapi.R
import com.androbim.reddyapi.databinding.ActivityUpdateUserBinding
import com.androbim.reddyapi.gorest.gorestApi.GorestApi
import com.androbim.reddyapi.gorest.gorestResponse.UserParameters
import com.androbim.reddyapi.gorest.gorestResponse.UserResponse
import com.androbim.reddyapi.gorest.goretRetrofitInstance.GorestInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateUserBinding
    lateinit var gorestApi: GorestApi
    lateinit var progressDialog: ProgressDialog
    lateinit var userParameters: UserParameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gorestApi=GorestInstance.getInstance().create(GorestApi::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)

        binding.deleteUser.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("Deleting User!!")

            val call=gorestApi.goRestDelete(binding.userId.text.toString());
            call.enqueue(object :Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    progressDialog.dismiss()

                    Toast.makeText(this@UpdateUserActivity, ""+response.body(), Toast.LENGTH_SHORT).show();
                    when(response.code())
                    {
                        200->Log.d("TAG", "onResponse: 201")
                        201-> Log.d("TAG", "onResponse: 201")
                        204->{
                            Toast.makeText(this@UpdateUserActivity, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                        }
                        304-> Log.d("TAG", "onResponse: 304")
                        400-> Log.d("TAG", "onResponse: 400")
                        401-> Log.d("TAG", "onResponse: 401")
                        403-> Log.d("TAG", "onResponse: 403")
                        404-> {
                            Toast.makeText(this@UpdateUserActivity, "No record found", Toast.LENGTH_SHORT).show();
                        }
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

        binding.updateUser.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("updating User!!")
            userParameters= UserParameters(binding.userName.text.toString(),binding.userMail.text.toString(),
            binding.userGender.selectedItem.toString(),"active");

            val call=gorestApi.goRestUpdate(binding.userId.text.toString(),userParameters)
            call.enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    progressDialog.dismiss()

                    Toast.makeText(this@UpdateUserActivity, ""+response.body(), Toast.LENGTH_SHORT).show();
                    when(response.code())
                    {
                        200->{
                            Toast.makeText(this@UpdateUserActivity, "Update Sucessful", Toast.LENGTH_SHORT).show();
                        }
                        201-> Log.d("TAG", "onResponse: 201")
                        204-> Log.d("TAG", "onResponse: 204")
                        304-> Log.d("TAG", "onResponse: 304")
                        400-> Log.d("TAG", "onResponse: 400")
                        401-> Log.d("TAG", "onResponse: 401")
                        403-> Log.d("TAG", "onResponse: 403")
                        404-> {
                            Toast.makeText(this@UpdateUserActivity, "No record found", Toast.LENGTH_SHORT).show();
                        }
                        405-> Log.d("TAG", "onResponse: 405")
                        415-> Log.d("TAG", "onResponse: 415")
                        422-> Log.d("TAG", "onResponse: 422")
                        429-> Log.d("TAG", "onResponse: 429")
                        500-> Log.d("TAG", "onResponse: 500")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(this@UpdateUserActivity, ""+t.localizedMessage, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}