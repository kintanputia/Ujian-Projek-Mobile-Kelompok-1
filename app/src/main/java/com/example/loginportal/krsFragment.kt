package com.example.loginportal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.loginportal.helper.*
import kotlinx.android.synthetic.main.fragment_matkul.*
import org.json.JSONObject


class krsFragment : Fragment() {

    var arrayList = ArrayList<krs>()
    lateinit var prefHelper: PrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_krs, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        prefHelper = PrefHelper(context)
        loadKRS()
    }

    private fun loadKRS(){
        var nim = prefHelper.getString(Constant.PREF_ID)
        AndroidNetworking.get(API.READKRS + "?nim=" + nim)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        arrayList.clear()

                        val jsonArray = response?.optJSONArray("result")

                        if (jsonArray?.length() == 0) {
                            Toast.makeText(
                                    activity, "Tidak ada mata kuliah yang diambil",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }

                        for (i in 0 until jsonArray?.length()!!) {

                            val jsonObject = jsonArray?.optJSONObject(i)
                            arrayList.add(
                                    krs(
                                            jsonObject.getString("kode"),
                                            jsonObject.getString("mata_kuliah"),
                                            jsonObject.getString("sks"),
                                            jsonObject.getString("nama_dosen"),
                                            jsonObject.getString("status_krs")
                                    )
                            )

                            if (jsonArray?.length() - 1 == i) {

                                val adapter = KRSAdapter(activity!!, arrayList)
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