package com.example.loginportal.helper

class API {
    companion object {

        private val SERVER = "http://192.168.43.195/loginPortal/"
        //val CREATE = SERVER+"create.php"
        val READ = SERVER +"readData.php"
        val READMATKUL = SERVER +"readMatkul.php"
        val READKRS = SERVER +"readKRS.php"
        val READKHS= SERVER+"readKHS.php"
        //val UPDATE = SERVER+"update.php"
        val CEKLOGIN = SERVER +"login.php"
    }
}