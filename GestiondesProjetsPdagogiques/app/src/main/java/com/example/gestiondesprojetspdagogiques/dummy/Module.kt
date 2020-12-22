package com.example.gestiondesprojetspdagogiques.dummy

import android.widget.Adapter
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

data class Module (val intitule : String , val objectif : String , val prerequis :String , val notemod : String
                   ,val noteval : String , val notevalidation : String ,val nomElement1: String
                   , val nomlement2 : String , val nomlement3 : String ,val descElement1: String , val descElement2: String
                   ,val descElement3 : String
                   )

/*
data class Module (val intitule : String , val objectif : String , val prerequis :String , val notemod : String
                   ,val noteval : String , val notevalidation : String , val element : ArrayList<Element>
)
*/
