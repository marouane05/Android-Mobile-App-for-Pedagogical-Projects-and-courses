package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
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
import org.w3c.dom.Text

class Parametre : AppCompatActivity() {

    lateinit var session : SessionManager

lateinit var  text : TextView
    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/getUser.php"
    private val url2 : String =Base+"/projet-pedagogique/api/controller/Update.php"
lateinit var reponse : TextView
    lateinit var emailuser : TextView
    lateinit var naissanceuser : TextView
    lateinit var passworduser : TextView
    lateinit var passwordv : TextView
    lateinit var edittele : ImageView
    lateinit var editpassword : ImageView
    lateinit var teluser : TextView
   lateinit var  telemodifier : EditText
  lateinit var nouveaupass : EditText
    lateinit var renouveaupass : EditText
    lateinit var repeatpass : ImageView
    lateinit var save : Button
    lateinit var back : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parametre)
//back = this.findViewById(R.id.back)
        //set actionbar title
      //  actionbar!!.title = "New Activity"
        //set back button

        text = this.findViewById(R.id.nomuser)
        back = this.findViewById(R.id.back)
        emailuser=this.findViewById(R.id.emailuser)
        naissanceuser=this.findViewById(R.id.naissanceuser)
        passworduser=this.findViewById(R.id.passworduser)
       // passwordv =this.findViewById(R.id.passwordv)
        edittele=this.findViewById(R.id.edittele)
        editpassword=this.findViewById(R.id.editpassword)
        teluser=this.findViewById(R.id.teluser)
        telemodifier = this.findViewById(R.id.telemodfier)
        nouveaupass = this.findViewById(R.id.nouveaupass)
        renouveaupass = this.findViewById(R.id.renouveaupass)
        repeatpass=this.findViewById(R.id.repeatpass)
        save = this.findViewById(R.id.save)
        reponse = this.findViewById(R.id.reponse)

        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()
        val email = user.get(SessionManager.KEY_EMAIL)
        val profession = user.get(SessionManager.KEY_PROFESSION).toString()


        var nom :String =""
        var prenom : String =""
        var tele : String =""
        var naissance : String =""
        var departement: String =" "
        var passtext1 : String = ""
        var passtext2 :String = passtext1
// lancer comme invisible nouveau pass les input ....
telemodifier.visibility= View.INVISIBLE;
nouveaupass.visibility=View.INVISIBLE
repeatpass.visibility=View.INVISIBLE
renouveaupass.visibility=View.INVISIBLE

        /** requete volley  **/
        Toast.makeText(applicationContext,profession, Toast.LENGTH_LONG).show()

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
                  //  departement = jsonInner.getString("departement").toString()

                }
                text.text="Mr. "+nom+" "+prenom
                emailuser.text =email
                naissanceuser.text= naissance
                teluser.text=tele

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
                params.put("email",email.toString())
                params.put("profession",profession)



                return params
            }




        }



        queue.add(req)
        queue.start()


        /** la fin de la requete **/



























back.setOnClickListener {


    if(profession.equals("Professeur")){
    val intent = Intent(applicationContext, ProfileActivity::class.java)
    /* intent.putExtra("email",str_test)
     intent.putExtra("nom",str_nom)
     intent.putExtra("prenom",str_prenom)
     intent.putExtra("profession",professionUser) */

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    startActivity(intent) }

    if(profession.equals("Administrateur")){

        val intent = Intent(applicationContext, ProfileAdmin::class.java)
        /* intent.putExtra("email",str_test)
         intent.putExtra("nom",str_nom)
         intent.putExtra("prenom",str_prenom)
         intent.putExtra("profession",professionUser) */

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)


    }

    if(profession.equals("Etudiant")) {

        val intent = Intent(applicationContext, ProfileEtudiant::class.java)
        /* intent.putExtra("email",str_test)
         intent.putExtra("nom",str_nom)
         intent.putExtra("prenom",str_prenom)
         intent.putExtra("profession",professionUser) */

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)



    }


}

edittele.setOnClickListener {
   teluser.visibility=View.INVISIBLE

   telemodifier.visibility = View.VISIBLE;
    //les concernés
    passworduser.visibility=View.VISIBLE
    nouveaupass.visibility=View.INVISIBLE
    repeatpass.visibility=View.INVISIBLE
    renouveaupass.visibility=View.INVISIBLE



}



 editpassword.setOnClickListener {

            teluser.visibility=View.VISIBLE

            telemodifier.visibility = View.INVISIBLE;

            //les concernés
            passworduser.visibility=View.INVISIBLE
            nouveaupass.visibility=View.VISIBLE
            repeatpass.visibility=View.VISIBLE
            renouveaupass.visibility=View.VISIBLE

     if(!telemodifier.text.toString().equals("")){
         teluser.text = telemodifier.text
     }





        }



     save.setOnClickListener {

         teluser.text = telemodifier.text
         if(teluser.text.toString().equals("")){
             teluser.text=tele
         }


         passtext1 = nouveaupass.text.toString().trim()
         passtext2 = renouveaupass.text.toString().trim()

         telemodifier.visibility = View.INVISIBLE;
         teluser.visibility=View.VISIBLE

         nouveaupass.visibility=View.INVISIBLE
         repeatpass.visibility=View.INVISIBLE
         renouveaupass.visibility=View.INVISIBLE
         passworduser.visibility = View.VISIBLE



         if(!passtext1.equals(passtext2)){
             renouveaupass.error="Le mot de passe ne correspond pas"
             renouveaupass.requestFocus()
             reponse.text="erreur en mot de passe"
             return@setOnClickListener
         }


         // requete 2


         val queue = Volley.newRequestQueue(this)


         // Request a string response from the provided URL.
         val req = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

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

                     departement = jsonInner.getString("departement").toString()

                 }

                 teluser.text=tele
                 passworduser.text="*****"

                 reponse.text="Bien modifier"



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
                 params.put("email",email.toString())
                 params.put("profession",profession)
                 params.put("naissance",naissance)
                 params.put("password",passtext1)
                 params.put("tele",teluser.text.toString())



                 return params
             }




         }



         queue.add(req)
         queue.start()













     }




    }


}
