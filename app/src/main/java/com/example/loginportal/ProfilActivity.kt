package com.example.loginportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.loginportal.helper.API
import com.example.loginportal.helper.Constant
import com.example.loginportal.helper.PrefHelper
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.android.synthetic.main.fragment_home.cekJenjang
import kotlinx.android.synthetic.main.fragment_home.cekJurusan
import kotlinx.android.synthetic.main.fragment_home.cekNama
import kotlinx.android.synthetic.main.fragment_home.cekNim
import org.json.JSONObject

class ProfilActivity : AppCompatActivity() {

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        prefHelper = PrefHelper(this)
        loadData()
        logout()
    }

    private fun loadData(){
        var nim = prefHelper.getString(Constant.PREF_ID)
        AndroidNetworking.get(API.READ + "?nim=" + nim)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

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
                        val address = jsonObject!!.getString("alamat")
                        val TTL = jsonObject!!.getString("ttl")
                        val religion = jsonObject!!.getString("agama")
                        val jenkel = jsonObject!!.getString("jenis_kelamin")
                        val sma = jsonObject!!.getString("asal_slta")
                        val tglDaftar = jsonObject!!.getString("tanggal_terdaftar")
                        val namaOrtu = jsonObject!!.getString("nama_ortu")
                        val alaOrtu = jsonObject!!.getString("alamat_ortu")
                        val kewarga = jsonObject!!.getString("warga_negara")
                        val state = jsonObject!!.getString("status")
                        val levJalur = jsonObject!!.getString("level/jalur_masuk")
                        val ukt = jsonObject!!.getString("besar_ukt")

                        cekNim.setText(nimm)
                        cekNama.setText(nama)
                        cekJenjang.setText(jenjang)
                        cekJurusan.setText(jurusan)
                        lbAlamat2.setText(address)
                        lbTTL2.setText(TTL)
                        lbAgama2.setText(religion)
                        lbJenkel2.setText(jenkel)
                        lbSLTA2.setText(sma)
                        lbTglTerdaftar2.setText(tglDaftar)
                        lbNamaOrtu2.setText(namaOrtu)
                        lbAlamatOrtu2.setText(alaOrtu)
                        lbWarneg2.setText(kewarga)
                        lbStatus2.setText(state)
                        lbLJ2.setText(levJalur)
                        lbUKT2.setText(ukt)


                        //Toast.makeText(applicationContext, "Connection Succes", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.d("ONERROR", anError?.errorDetail?.toString()!!)
                    Toast.makeText(this@ProfilActivity, "Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun logout(){
        btn_logout.setOnClickListener(){
            prefHelper.clear()
            Toast.makeText(this, "Keluar", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}