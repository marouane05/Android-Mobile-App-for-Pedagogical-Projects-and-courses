package com.example.gestiondesprojetspdagogiques.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesprojetspdagogiques.R


class ElementAdapter  (val ElementList : ArrayList<Element>): RecyclerView.Adapter<ElementAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ElementAdapter.ViewHolder, position: Int) {
        holder.bindItems(ElementList[position])

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.elements, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ElementList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(element : Element) {



            var nomelement1 : TextView  = itemView.findViewById(R.id.nomelement1) as TextView
            var descelement1 : TextView  = itemView.findViewById(R.id.descelem1) as TextView


        nomelement1.text = element.intitule
            descelement1.text = element.description + "Avec un volume horaire "+element.heure



            //  nomelement1.text = module.ListElemen.g
        }
    }







}