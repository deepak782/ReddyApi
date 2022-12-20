package com.androbim.reddyapi.object1

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.androbim.reddyapi.R
import com.androbim.reddyapi.api.ApiInterface
import com.androbim.reddyapi.databinding.ActivityList2Binding
import com.androbim.reddyapi.utilites.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class List2Activity : AppCompatActivity() {
    lateinit var binding: ActivityList2Binding
    lateinit var apiInterface: ApiInterface
    lateinit var progressDialog: ProgressDialog
    var arrayList=ArrayList<String>()
    lateinit var arrayAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityList2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface=RetrofitInstance.getInstance().create(ApiInterface::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)
        binding.submitBtn1.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("moviz list loading..")

            val datalist=apiInterface.getData3()
            datalist.enqueue(object :Callback<ListModel1>{
                override fun onResponse(call: Call<ListModel1>, response: Response<ListModel1>) {
                    progressDialog.dismiss()

                    val moviz: List<Moviz> = response.body()!!.moviz
                    for(myData in moviz)
                    {
                        val albumId=myData.albumId
                        val id=myData.id
                        val thumbnailUrl=myData.thumbnailUrl
                        val title=myData.title
                        val url=myData.url

                        arrayList.add("$albumId \n $id \n $thumbnailUrl \n $title \n$url")

                        arrayAdapter= ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,arrayList);
                        binding.SimpleList1.adapter=arrayAdapter
                    }
                }

                override fun onFailure(call: Call<ListModel1>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }
    }
}