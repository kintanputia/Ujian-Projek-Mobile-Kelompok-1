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
import com.example.loginportal.helper.login
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var arrayList = ArrayList<login>()
    var nimm : String =""
    var pasword : String =""


    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        prefHelper = PrefHelper(this)
    }

    override fun onResume(){
        super.onResume()
        tryLogin()
    }

    override fun onStart() {
        super.onStart()
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            moveIntent()
        }
    }

    private fun cekLogin() {
        val nim = inputNIM.text.toString()
        val pass = inputPass.text.toString()
            AndroidNetworking.get(API.CEKLOGIN + "?nim=" + inputNIM.text.toString())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject?) {
                            arrayList.clear()

                            val jsonArray = response?.optJSONArray("result")

                            if (jsonArray!!.length() == 0) {
                                Toast.makeText(applicationContext, "NIM tidak ditemukan", Toast.LENGTH_SHORT).show()
                            } else {
                                val jsonObject = jsonArray.optJSONObject(0)
                                nimm = jsonObject!!.getString("nim")
                                pasword = jsonObject!!.getString("password")


                                if (inputPass.text.isEmpty()){
                                    Toast.makeText(applicationContext, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                                }
                                else if (nim == nimm && pass == pasword){
                                    saveSession(nim, pass)
                                    Toast.makeText(applicationContext, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
                                    moveIntent()
                                }
                                else {
                                    Toast.makeText(applicationContext, "Password Salah", Toast.LENGTH_SHORT).show()
                                }
                                //Toast.makeText(applicationContext, "Connection Succes", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(anError: ANError?) {
                            Log.d("ONERROR", anError?.errorDetail?.toString()!!)
                            Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT).show()
                        }
                    })
    }

    private fun tryLogin(){
        btnLogin.setOnClickListener() {
            if (inputNIM.text.isEmpty()) {
                Toast.makeText(applicationContext, "NIM tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }else {
                cekLogin()
            }
        }
    }

    private fun saveSession(id : String ,pass : String){
        prefHelper.put( Constant.PREF_ID, id )
        prefHelper.put( Constant.PREF_PASS, pass )
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    private fun moveIntent(){
        Intent(this@MainActivity, BottomActivity::class.java).also {
            startActivity(it) }
        finish()
    }
}



