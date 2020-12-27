package com.example.loginportal.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginportal.R
import kotlinx.android.synthetic.main.matkul_list.view.*

class MatkulAdapter (private val context: Context, private val arrayList: ArrayList<subject>) : RecyclerView.Adapter<MatkulAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.matkul_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbKode.text = arrayList?.get(position)?.kode
        holder.view.lbMatkul.text = arrayList?.get(position)?.matkul
        holder.view.lbNamDos.text = arrayList?.get(position)?.dosen
        holder.view.lbKelas.text = arrayList?.get(position)?.kelas
        holder.view.lbWapil.text = arrayList?.get(position)?.wapil
        holder.view.lbSKS.text = arrayList?.get(position)?.Sks
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}