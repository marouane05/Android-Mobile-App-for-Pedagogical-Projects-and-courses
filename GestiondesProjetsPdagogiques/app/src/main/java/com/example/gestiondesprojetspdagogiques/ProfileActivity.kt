package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.android.volley.*

import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ProfileActivity : AppCompatActivity()  {

    lateinit var textView: TextView
    lateinit var card : CardView
    lateinit var cardOut : CardView
    lateinit var cardProjet : CardView
    lateinit var cardNotification : CardView
    lateinit var cardAjoutCours: CardView
    private val conf : config= config()
   private var Base :String= conf.Base
   private val url: String = Base+"/projet-pedagogique/api/controller/getUser.php"
    lateinit var session : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)




        textView = findViewById<TextView>(R.id.txtadmin)
        card = findViewById(R.id.parametre)
        cardOut = findViewById(R.id.quitter)
        cardProjet = findViewById(R.id.addprojet)
        cardNotification = this.findViewById(R.id.notification)
        cardAjoutCours = this.findViewById(R.id.ajoutcours)

    var nom :String =""
        var prenom : String =""
   var tele : String=""
    var naissance : String =""
    var departement : String=""
     var id : String =""
     var profession :String =""

        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()
        val email = user.get(SessionManager.KEY_EMAIL).toString()
       profession = user.get(SessionManager.KEY_PROFESSION).toString()

            // on commence la requete volley
        //**********************************************************************


        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req = object : StringRequest(Request.Method.POST, url, Response.Listener <String> {

                response1  ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")
                var str_user: String = ""



                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                    nom =jsonInner.get("nom").toString()
                    prenom = jsonInner.get("prenom").toString()
                    tele = jsonInner.get("tele").toString()
                    naissance=jsonInner.getString("naissance").toString()
                    departement = jsonInner.getString("departement").toString()
                    id = jsonInner.get("id").toString()

                }
txtadmin.text="Mr. "+nom+" "+prenom


            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin


        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()
                params.put("email",email)
                params.put("profession",profession)



                return params
            }




        }



        queue.add(req)
        queue.start()


card.setOnClickListener {

   /* intent.putExtra("email",str_test)
    intent.putExtra("nom",str_nom)
    intent.putExtra("prenom",str_prenom)
    intent.putExtra("profession",professionUser) */

  val intent = Intent(applicationContext, Parametre::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    startActivity(intent)



}

cardOut.setOnClickListener {

    session.LogoutUser()


}


        cardProjet.setOnClickListener {

            val intent = Intent(applicationContext, Ppedagogique::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id",id)
            startActivity(intent)






        }



        cardNotification.setOnClickListener {

            val intent = Intent(applicationContext, Notification::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id",id)
            intent.putExtra("prof",nom+" "+prenom)
            intent.putExtra("email",email)
            intent.putExtra("tele",tele)
            startActivity(intent)


        }


        cardAjoutCours.setOnClickListener {

            val intent = Intent(applicationContext, AjoutCours::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id",id)
            startActivity(intent)


        }



    }







}
