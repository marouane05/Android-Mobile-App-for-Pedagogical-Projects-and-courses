package com.example.gestiondesprojetspdagogiques.dummy

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.gestiondesprojetspdagogiques.R

class MyListProgrammeAdapter(private val context: Activity, private val semestre: Array<String>, private val modules: Array<String>) :
    ArrayAdapter<String>(context, R.layout.programme,semestre){


    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.programme, null, true)

        val notresemestre = rowView.findViewById(R.id.semestre) as TextView
        val notremodules = rowView.findViewById(R.id.modules) as TextView

       notresemestre.text = semestre[position]
       notremodules.text = modules[position]


        return rowView
    }


}