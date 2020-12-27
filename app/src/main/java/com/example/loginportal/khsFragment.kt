package com.example.loginportal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.loginportal.helper.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_matkul.*
import org.json.JSONObject

class khsFragment : Fragment() {

    var arrayList = ArrayList<khs>()
    lateinit var prefHelper: PrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_khs, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        prefHelper = PrefHelper(context)
        loadKHS()
    }

    private fun loadKHS(){
        var nim = prefHelper.getString(Constant.PREF_ID)
        AndroidNetworking.get(API.READKHS + "?nim=" + nim)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0) {
                        Toast.makeText(
                            activity, "Tidak ada nilai yang ditampilkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    for (i in 0 until jsonArray?.length()!!) {

                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add(
                            khs(
                                jsonObject.getString("kode"),
                                jsonObject.getString("mata_kuliah"),
                                jsonObject.getString("kelas"),
                                jsonObject.getString("w/p"),
                                jsonObject.getString("sks"),
                                jsonObject.getString("nilai")
                            )
                        )

                        if (jsonArray?.length() - 1 == i) {

                            val adapter = KHSAdapter(activity!!, arrayList)
                            adapter.notifyDataSetChanged()
                            mRecyclerView.adapter = adapter

                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    Log.d("ONERROR", anError?.errorDetail?.toString()!!)
                    Toast.makeText(activity, "Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

}