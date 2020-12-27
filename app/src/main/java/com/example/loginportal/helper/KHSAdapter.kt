package com.example.loginportal.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginportal.R
import kotlinx.android.synthetic.main.fragment_khs.view.*
import kotlinx.android.synthetic.main.khs_list.view.*

class KHSAdapter (private val context: Context, private val arrayList: ArrayList<khs>) : RecyclerView.Adapter<KHSAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.khs_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lblKode.text = arrayList?.get(position)?.kode
        holder.view.lblMatkul.text = arrayList?.get(position)?.matkul
        holder.view.lblKelas.text = arrayList?.get(position)?.kelas
        holder.view.lblWapil.text = arrayList?.get(position)?.wp
        holder.view.lblSKS.text = arrayList?.get(position)?.sks
        holder.view.lblNilai.text = arrayList?.get(position)?.nilai
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}