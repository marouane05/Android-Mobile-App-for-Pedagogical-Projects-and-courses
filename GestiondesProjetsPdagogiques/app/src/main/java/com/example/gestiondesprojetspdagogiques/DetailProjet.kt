package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DetailProjet : AppCompatActivity() {

    lateinit var nom : TextView
    lateinit var description : TextView
    lateinit var  debouches : TextView
    lateinit var  cout : TextView
    lateinit var coordinateur : TextView
    lateinit var admission : TextView
    lateinit var  module1 : TextView
    lateinit var  module2 : TextView
    lateinit var  module3 : TextView
    lateinit var  module4 : TextView
    lateinit var  module5 : TextView
    lateinit var  module6 : TextView
    lateinit var  module7 : TextView
    lateinit var  module8 : TextView
    lateinit var  module9 : TextView
    lateinit var  module10 : TextView
    lateinit var  module11 : TextView
    lateinit var  module12 : TextView
    lateinit var  valider : Button
    lateinit var refuser : Button
    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/ProjetPrecis.php"
    private val url2: String = Base+"/projet-pedagogique/api/controller/findModule.php"
    private val url3: String = Base+"/projet-pedagogique/api/controller/validerProjet.php"
    lateinit var session : SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {






        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_projet)



        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()



        var id_projet:String = intent.getStringExtra("id")

        var cycle : String = intent.getStringExtra("cycle")


        nom  = this.findViewById(R.id.nom)
        admission = this.findViewById(R.id.admission)
        description= this.findViewById(R.id.objectif)
        debouches = this.findViewById(R.id.debouches)
        coordinateur = this.findViewById(R.id.coordinateur)
        // fin = this.findViewById(R.id.fin)

        module1 = this.findViewById(R.id.module1)
        module2 = this.findViewById(R.id.module2)
        module3 = this.findViewById(R.id.module3)
        module4 = this.findViewById(R.id.module4)
        module5 = this.findViewById(R.id.module5)
        module6 = this.findViewById(R.id.module6)
        module7 = this.findViewById(R.id.module7)
        module8 = this.findViewById(R.id.module8)
        module9 = this.findViewById(R.id.module9)
        module10 = this.findViewById(R.id.module10)
        module11 = this.findViewById(R.id.module11)
        module12 = this.findViewById(R.id.module12)

        valider = this.findViewById(R.id.valider)
        refuser = this.findViewById(R.id.refuser)

        var nom_prof :String= ""
        var prenom_prof : String = ""
        var debouches_projet : String = ""
        var admission_projet : String =" "
        var  description_projet :String=""
        var nom_projet : String =""
        var email_prof:String=""







        /** requete volley  **/

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


                    nom_prof =jsonInner.get("nom").toString()
                    prenom_prof = jsonInner.get("prenom").toString()
                   nom_projet = jsonInner.get("nom_projet").toString()
                   admission_projet =jsonInner.getString("admission").toString()
                    description_projet = jsonInner.getString("description").toString()
                    email_prof = jsonInner.getString("email")
                    debouches_projet = jsonInner.getString("debouche")

                }


            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
nom.setText("  "+cycle+" "+nom_projet)
description.setText(description_projet)
admission.setText(admission_projet)
debouches.setText(debouches_projet)
coordinateur.setText("Mr. "+nom_prof+" "+prenom_prof+"\n"+email_prof+"")

        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()
                params.put("id",id_projet)




                return params
            }




        }



        queue.add(req)
        queue.start()


        /** la fin de la requete **/



  var listModule :ArrayList<String> = ArrayList<String>()
  var listHeure :ArrayList<String> = ArrayList<String>()



        /** requete volley  **/

    val queue2 = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req2 = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

                response1  ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")
                var str_user: String = ""



                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    listModule.add(jsonInner.get("nom_module").toString())
                    listHeure.add(jsonInner.get("nbr_heure").toString())

                }


            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

            module1.setText(listModule.get(0).toString()+"("+listHeure.get(0).toString()+" H)")
            module2.setText(listModule.get(1).toString()+"("+listHeure.get(1).toString()+" H)")
            module3.setText(listModule.get(2).toString()+"("+listHeure.get(2).toString()+" H)")
            module4.setText(listModule.get(3).toString()+"("+listHeure.get(3).toString()+" H)")
            module5.setText(listModule.get(4).toString()+"("+listHeure.get(4).toString()+" H)")
            module6.setText(listModule.get(5).toString()+"("+listHeure.get(5).toString()+" H)")
            module7.setText(listModule.get(6).toString()+"("+listHeure.get(6).toString()+" H)")
            module8.setText(listModule.get(7).toString()+"("+listHeure.get(7).toString()+" H)")
            module9.setText(listModule.get(8).toString()+"("+listHeure.get(8).toString()+" H)")
            module10.setText(listModule.get(9).toString()+"("+listHeure.get(9).toString()+" H)")
            module11.setText(listModule.get(10).toString()+"("+listHeure.get(10).toString()+" H)")
            module12.setText(listModule.get(11).toString()+"("+listHeure.get(11).toString()+" H)")






        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()
                params.put("id",id_projet)




                return params
            }




        }



        queue2.add(req2)
        queue2.start()


        /** la fin de la requete **/






   valider.setOnClickListener {

       /** requete volley  **/

       val queue = Volley.newRequestQueue(this)


       // Request a string response from the provided URL.
       val req = object : StringRequest(Request.Method.POST, url3, Response.Listener <String> {

               response1  ->
           try {

               val obj = JSONObject(response1)
               val jsonArray: JSONArray = obj.getJSONArray("data")
               var str_user: String = ""



               for (i in 0 until jsonArray.length()) {
                   var jsonInner: JSONObject = jsonArray.getJSONObject(i)



               }


           } catch (e: JSONException){ e.printStackTrace()}
           // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
           Toast.makeText(applicationContext,"valider", Toast.LENGTH_LONG).show()
           val intent = Intent(applicationContext, ProfileAdmin::class.java)
           /* intent.putExtra("email",str_test)
            intent.putExtra("nom",str_nom)
            intent.putExtra("prenom",str_prenom)
            intent.putExtra("profession",professionUser) */

           intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

           startActivity(intent)


       },object : Response.ErrorListener {
           override fun onErrorResponse(error: VolleyError?) {


               //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
           }
       })   {

           @Throws(AuthFailureError::class)
           override fun getParams(): Map<String, String> {

               val params = HashMap<String,String>()
               params.put("id",id_projet)
               params.put("situation","oui")




               return params
           }




       }



       queue.add(req)
       queue.start()


       /** la fin de la requete **/









   }




refuser.setOnClickListener {



    /** requete volley  **/

    val queue = Volley.newRequestQueue(this)


    // Request a string response from the provided URL.
    val req = object : StringRequest(Request.Method.POST, url3, Response.Listener <String> {

            response1  ->
        try {

            val obj = JSONObject(response1)
            val jsonArray: JSONArray = obj.getJSONArray("data")
            var str_user: String = ""



            for (i in 0 until jsonArray.length()) {
                var jsonInner: JSONObject = jsonArray.getJSONObject(i)



            }


        } catch (e: JSONException){ e.printStackTrace()}
        // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
        Toast.makeText(applicationContext,"refuser", Toast.LENGTH_LONG).show()

        val intent = Intent(applicationContext, ProfileAdmin::class.java)
        /* intent.putExtra("email",str_test)
         intent.putExtra("nom",str_nom)
         intent.putExtra("prenom",str_prenom)
         intent.putExtra("profession",professionUser) */

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)



    },object : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {


            //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
        }
    })   {

        @Throws(AuthFailureError::class)
        override fun getParams(): Map<String, String> {

            val params = HashMap<String,String>()
            params.put("id",id_projet)
            params.put("situation","non")




            return params
        }




    }



    queue.add(req)
    queue.start()


    /** la fin de la requete **/












}











    }
}
