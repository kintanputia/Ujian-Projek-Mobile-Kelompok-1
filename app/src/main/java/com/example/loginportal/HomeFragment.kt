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
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.loginportal.helper.API
import com.example.loginportal.helper.Constant
import com.example.loginportal.helper.PrefHelper
import com.example.loginportal.helper.login
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {
    lateinit var prefHelper: PrefHelper
    var arrayList = ArrayList<login>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        prefHelper = PrefHelper(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardview.setOnClickListener(){
            startActivity(Intent(activity, ProfilActivity::class.java))
        }
        btn_keluar.setOnClickListener(){
            prefHelper.clear()
            Toast.makeText(activity, "Keluar", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, MainActivity::class.java))
        }
        loadData()

    }
    private fun loadData(){
        var nim = prefHelper.getString(Constant.PREF_ID)
        AndroidNetworking.get(API.READ + "?nim=" + nim)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray!!.length() == 0) {
                        //Toast.makeText(applicationContext, "DATABASE KOSONG", Toast.LENGTH_SHORT).show()
                    } else {
                        val jsonObject = jsonArray.optJSONObject(0)
                        val nimm = jsonObject!!.getString("nim")
                        val sks = jsonObject!!.getString("sks")
                        val nama = jsonObject!!.getString("nama")
                        val ipk = jsonObject!!.getString("ipk")
                        val jenjang = jsonObject!!.getString("jenjang")
                        val jurusan = jsonObject!!.getString("jurusan")

                        cekNim.setText(nimm)
                        cekNama.setText(nama)
                        IPK.setText(ipk)
                        cekSks.setText(sks)
                        cekJenjang.setText(jenjang)
                        cekJurusan.setText(jurusan)

                        saveData(jurusan)


                        //Toast.makeText(applicationContext, "Connection Succes", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.d("ONERROR", anError?.errorDetail?.toString()!!)
                    Toast.makeText(activity, "Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun saveData (prodi:String){
        prefHelper.put(Constant.PREF_JURUSAN, prodi)
    }

}