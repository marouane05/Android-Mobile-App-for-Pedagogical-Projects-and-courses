package com.example.gestiondesprojetspdagogiques.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondesprojetspdagogiques.R

class ModuleAdapter(val ModuleList : ArrayList<Module>): RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ModuleAdapter.ViewHolder, position: Int) {
        holder.bindItems(ModuleList[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.modules, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ModuleList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(module : Module) {


     val objmodule : TextView=itemView. findViewById(R.id.objmodule) as TextView
val nommodule : TextView=itemView. findViewById(R.id.nommodule) as TextView
        val prerequismodule : TextView = itemView.findViewById(R.id.prerequismodule) as TextView

   var nomelement1 : TextView  = itemView.findViewById(R.id.nomelement1) as TextView
  var descelement1 : TextView  = itemView.findViewById(R.id.descelem1) as TextView
            var nomelement2 : TextView  = itemView.findViewById(R.id.nomelement2) as TextView
            var descelement2 : TextView  = itemView.findViewById(R.id.descelem2) as TextView
            var nomelement3 : TextView  = itemView.findViewById(R.id.nomelement3) as TextView
            var descelement3 : TextView  = itemView.findViewById(R.id.descelem3) as TextView


 val noteval : TextView  = itemView.findViewById(R.id.noteval) as TextView
   val notemod : TextView  = itemView.findViewById(R.id.notemod) as TextView
   val notevalidation : TextView  = itemView.findViewById(R.id.notevalidation) as TextView



             nomelement1.text = module.nomElement1
            nomelement2.text = module.nomlement2
            nomelement3.text = module.nomlement3

            descelement1.text= module.descElement1
            descelement2.text= module.descElement2
            descelement3.text= module.descElement3

/*
          nomelement1.text = module.element.get(0).intitule
          //  nomelement2.text = module.element.get(1).intitule
          //  nomelement3.text = module.element.get(2).intitule

            descelement1.text= module.element.get(0).description
          //  descelement2.text= module.element.get(0).description
          //  descelement3.text= module.element.get(0).description

*/         nommodule.text = "MODULE : "+module.intitule
            objmodule.text = module.objectif
            prerequismodule.text = module.prerequis
            noteval.text = module.noteval
            notemod.text = module.notemod
            notevalidation.text = module.notevalidation

          //  nomelement1.text = module.ListElemen.g
        }
    }







}