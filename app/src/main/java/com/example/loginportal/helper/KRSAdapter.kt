package com.example.loginportal.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginportal.R
import kotlinx.android.synthetic.main.fragment_krs.view.*

class KRSAdapter (private val context: Context, private val arrayList: ArrayList<krs>) : RecyclerView.Adapter<KRSAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.krs_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbCode.text = arrayList?.get(position)?.kode
        holder.view.lbMataKuliah.text = arrayList?.get(position)?.matkul
        holder.view.lbsks.text = arrayList?.get(position)?.Sks
        holder.view.lbDosen.text = arrayList?.get(position)?.dosen
        holder.view.lbStatusKRS.text = arrayList?.get(position)?.status
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}