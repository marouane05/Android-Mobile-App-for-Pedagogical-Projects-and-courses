package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProjetAttente : AppCompatActivity() {

    lateinit var  mess : TextView
    lateinit var btnOk : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet_attente)

        mess = this.findViewById(R.id.mess)
        btnOk = this.findViewById(R.id.btnokk)

        var result1 = intent.getStringExtra("res1")
        var result2 = intent.getStringExtra("res2")

        var val1 : Int = result1.toInt()
        var val2 : Int = result2.toInt()
        var diff : Int = (val1-val2)

        mess.setText("Votre projet est en attente, il reste "+diff+" Modules qui n'ont pas encore rempli ")

        btnOk.setOnClickListener {






            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // intent.putExtra("diff",diff)
            startActivity(intent)







        }

    }
}
