package com.androbim.reddyapi.git

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.androbim.reddyapi.R
import com.androbim.reddyapi.databinding.ActivityGitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class GitActivity : AppCompatActivity() {
    lateinit var binding:ActivityGitBinding
    lateinit var apiInterface: GitApiInterface
    lateinit var progressDialog: ProgressDialog
    var arrayList=ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiInterface=GitRetrofitInstance.getInstance().create(GitApiInterface::class.java)
        progressDialog= ProgressDialog(this)
        progressDialog.setCancelable(false)

        binding.gitBtn.setOnClickListener {
            progressDialog.show()
            progressDialog.setMessage("loading Git List.....")

            val gitData=apiInterface.getDataFromGitApi("Hyderabad")
            gitData.enqueue(object :Callback<GitModel>{
                override fun onResponse(call: Call<GitModel>, response: Response<GitModel>) {
                    progressDialog.dismiss()

                    val itemsList:List<GitList> =response.body()!!.items
                    for(myData in itemsList)
                    {
                        val name=myData.name
                        val fullName=myData.full_name
                        val desc=myData.description
                        val owner1=Owner1(myData.owner.login,myData.owner.id)
                        val login=owner1.login
                        val id=owner1.id

                        arrayList.add("->$name\n->$fullName\n->$desc\n->$login->$id")
                        arrayAdapter=ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,arrayList);
                        binding.gitList.adapter=arrayAdapter

                    }
                }

                override fun onFailure(call: Call<GitModel>, t: Throwable) {
                    progressDialog.dismiss()
                }

            })
        }
    }
}