package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ProfileEtudiant : AppCompatActivity() {

    lateinit var cours : CardView
    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/getUser.php"
    lateinit var card : CardView
    lateinit var cardOut : CardView
    lateinit var session : SessionManager
    lateinit var txtetudiant : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_etudiant)

    cours = this.findViewById(R.id.cours)

txtetudiant = this.findViewById(R.id.txtetudiant)
        var nom :String =""
        var prenom : String =""
        var tele : String=""
        var naissance : String =""
        var filiere : String=""
        var id : String =""
        card = this.findViewById(R.id.parametre)
        cardOut = this.findViewById(R.id.quitter)

        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()
        val email = user.get(SessionManager.KEY_EMAIL).toString()
        val profession = user.get(SessionManager.KEY_PROFESSION).toString()


        /** Req Etudiant **/

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


                    nom +=jsonInner.get("nom").toString()
                    prenom += jsonInner.get("prenom").toString()
                    tele = jsonInner.get("tele").toString()
                    naissance=jsonInner.getString("naissance").toString()
                    filiere = jsonInner.getString("filiere").toString()
                    id = jsonInner.get("id").toString()

                }

                txtetudiant.text="Mr. "+nom+" "+prenom

            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

            Toast.makeText(applicationContext,id, Toast.LENGTH_LONG).show()


        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()
                params.put("email",email.toString())
                params.put("profession","Etudiant")



                return params
            }




        }



        queue.add(req)
        queue.start()



        /** Fin Req **/




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

        cours.setOnClickListener {


            val intent = Intent(applicationContext, Cours::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id",id)
            intent.putExtra("id_fil",filiere)
            startActivity(intent)





        }









    }


}
