package com.androbim.reddyapi.multipleObject

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.androbim.reddyapi.R
import com.androbim.reddyapi.api.ApiInterface
import com.androbim.reddyapi.databinding.ActivityList1Binding
import com.androbim.reddyapi.utilites.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class List1Activity : AppCompatActivity() {

    lateinit var binding:ActivityList1Binding
    lateinit var apiInterface: ApiInterface
    lateinit var progressDialog: ProgressDialog
    var arrayList=ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityList1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface=RetrofitInstance.getInstance().create(ApiInterface::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)

        binding.submitBtn1.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("Loading List...")

            val getData=apiInterface.getDate2()
            getData.enqueue(object :Callback<List<ListModelItem>>{
                override fun onResponse(
                    call: Call<List<ListModelItem>>,
                    response: Response<List<ListModelItem>>
                ) {
                    progressDialog.dismiss()

                    val listModelItem:List<ListModelItem> = response?.body()!!

                    for(myData in listModelItem)
                    {
                        val albumId=myData.albumId
                        val id=myData.id
                        val thumbnailUrl=myData.thumbnailUrl
                        val title=myData.title
                        val url=myData.url

                        arrayList.add("$albumId \n $id \n $thumbnailUrl \n $title \n$url")

                        arrayAdapter=ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,arrayList);
                        binding.SimpleList.adapter=arrayAdapter



                    }
                }

                override fun onFailure(call: Call<List<ListModelItem>>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }

    }
}