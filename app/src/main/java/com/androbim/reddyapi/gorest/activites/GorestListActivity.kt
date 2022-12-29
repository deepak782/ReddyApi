package com.androbim.reddyapi.gorest.activites

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androbim.reddyapi.R
import com.androbim.reddyapi.databinding.ActivityGorestListBinding
import com.androbim.reddyapi.gorest.gorestApi.GorestApi
import com.androbim.reddyapi.gorest.gorestResponse.GorestList
import com.androbim.reddyapi.gorest.gorestResponse.UserList
import com.androbim.reddyapi.gorest.goretRetrofitInstance.GorestInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GorestListActivity : AppCompatActivity() {
    lateinit var binding: ActivityGorestListBinding
    lateinit var gorestApi: GorestApi
    lateinit var arrayAdapter: ArrayAdapter<String>
    val arrayList=ArrayList<String>()
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGorestListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gorestApi=GorestInstance.getInstance().create(GorestApi::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)

        binding.searchBtn.setOnClickListener {
            listUser()
        }

        binding.createBtn.setOnClickListener {

            startActivity(Intent(this@GorestListActivity,CreateUserActivity::class.java))
        }

        binding.updateBtn.setOnClickListener {

            startActivity(Intent(this@GorestListActivity,UpdateUserActivity::class.java))
        }



    }

    private fun listUser() {
        progressDialog.show()
        progressDialog.setMessage("laoding.....")

        arrayList.clear()
        val call=gorestApi.goRestList();

        call.enqueue(object :Callback<List<GorestList>>{
            override fun onResponse(
                call: Call<List<GorestList>>,
                response: Response<List<GorestList>>
            ) {
                progressDialog.dismiss()
                when(response.code())
                {
                    200-> {

                        val gorestList: List<GorestList>? =response.body()
                        if (gorestList != null) {

                            for(myData in gorestList) {

                                val id=myData.id
                                val name=myData.name
                                val email=myData.email
                                val gender=myData.gender
                                val status=myData.status

                                arrayList.add("$id\n$name\n$email\n$gender\n$status")
                                arrayAdapter= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,arrayList)
                                binding.allList.adapter=arrayAdapter
                            }
                        }
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

            override fun onFailure(call: Call<List<GorestList>>, t: Throwable) {
                progressDialog.dismiss()
            }

        })


    }
}