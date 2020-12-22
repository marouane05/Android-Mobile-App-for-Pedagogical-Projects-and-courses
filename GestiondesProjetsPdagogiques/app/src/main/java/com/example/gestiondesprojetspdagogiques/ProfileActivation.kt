package com.example.gestiondesprojetspdagogiques

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_profile_activation.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ProfileActivation : AppCompatActivity() {


 lateinit  var  password : EditText
    lateinit var passwordVerification : EditText
   lateinit var btnCreer : Button
    val conf : config=config()
    var Base :String= conf.Base
    val url = Base+"/projet-pedagogique/api/controller/addUser.php"
    var str_test : String =""
lateinit var retour : LinearLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_activation)

        btnCreer = this.findViewById(R.id.btnCreer)
        password = this.findViewById(R.id.password1)
        passwordVerification = this.findViewById(R.id.password1verification)
        retour = this.findViewById(R.id.retour)
val var1 = "bonjour" +" "+intent.getStringExtra("nom") + " "+ intent.getStringExtra("prenom")
    //    Toast.makeText(applicationContext,var1, Toast.LENGTH_LONG).show()
  message.text= var1

 val emailRecu = intent.getStringExtra("email")
  val professionRecu = intent.getStringExtra("profession")





        retour.setOnClickListener {

            val intent = Intent(applicationContext, ActiverCompte::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


            startActivity(intent)



        }




        btnCreer.setOnClickListener {

if (password.text.toString().isEmpty()){

    password1.error="champ vide"
   password1.requestFocus()
    return@setOnClickListener


}

if(passwordVerification.text.toString().isEmpty()){
    password1verification.error="champ vide"
    return@setOnClickListener
}

if(! password.text.toString().trim().equals(passwordVerification.text.toString().trim())){
    password1verification.error="mot de passe différent"
    return@setOnClickListener

}


            // requete volley

            val queue = Volley.newRequestQueue(this@ProfileActivation)
            val req = object : StringRequest(Request.Method.POST,url, Response.Listener <String> {

                    response  ->
                try {

                    val obj = JSONObject(response)
                    val jsonArray: JSONArray = obj.getJSONArray("reponse")
                    var str_user: String = ""

                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        str_user = str_user + "\n" + jsonInner.get("succed")
                        str_test =jsonInner.get("succed").toString()
                    }


                    Toast.makeText(this, str_user, Toast.LENGTH_LONG).show()

                } catch (e: JSONException){ e.printStackTrace()}
                if(str_test.trim().equals("true")) {
                    Toast.makeText(this, "votre compte est activé !", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"Error votre compte est deja activé", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
                    params.put("email",emailRecu)
                    params.put("password",password.text.toString().trim())
                    params.put("profession",professionRecu)
                    return params
                }

            }



            queue.add(req)
            queue.start()











        }



    }





}
