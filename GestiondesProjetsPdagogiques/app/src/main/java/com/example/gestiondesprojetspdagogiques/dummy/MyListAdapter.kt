package com.example.gestiondesprojetspdagogiques.dummy

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gestiondesprojetspdagogiques.R


class MyListAdapter(private val context: Activity, private val profnom: Array<String>, private val email: Array<String> , private val teleprof : Array<String>)
    : ArrayAdapter<String>(context, R.layout.equipe_professeur,profnom) {


    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.equipe_professeur, null, true)

        val profName = rowView.findViewById(R.id.profnom) as TextView
        val emailText = rowView.findViewById(R.id.emailprof) as TextView
        val teleText = rowView.findViewById(R.id.teleprof) as TextView

        profName.text = profnom[position]
      emailText.text = email[position]
       teleText.text = teleprof[position]

        return rowView
    }

}