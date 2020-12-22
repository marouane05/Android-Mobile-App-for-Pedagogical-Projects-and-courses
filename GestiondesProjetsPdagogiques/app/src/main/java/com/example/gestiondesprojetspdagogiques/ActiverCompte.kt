package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_activer_compte.*
import kotlinx.android.synthetic.main.activity_activer_compte.Email
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.widget.Spinner as Spinner1
import com.android.volley.Response as Response1

class ActiverCompte : AppCompatActivity() {
lateinit var BtnContinuer : Button
lateinit var professionSpinner : Spinner1
    lateinit var email : EditText
lateinit var naissanceSpinner : DatePicker
    lateinit var  retour : LinearLayout
private var arrayProfession = R.array.profession
//lateinit var  resultChoice : TextView
private var professionUser : String =""
private  var naissanceUser : String =""
lateinit var text : TextView

    val conf : config=config()
    var Base :String= conf.Base
    val url = Base+"/projet-pedagogique/api/controller/findUser.php"


   // @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activer_compte)

        email=this.findViewById(R.id.Email)
professionSpinner = this.findViewById(R.id.profession)
retour = this.findViewById(R.id.retour)
        BtnContinuer = this.findViewById(R.id.btnCreer)
text = this.findViewById(R.id.activer)
        naissanceSpinner = this.findViewById(R.id.naissance)
      //  resultChoice = this.findViewById(R.id.resultChoice)




       ArrayAdapter.createFromResource(
            this,
            R.array.profession,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            professionSpinner.adapter = adapter
        }





        BtnContinuer.setOnClickListener {

             professionUser = professionSpinner.selectedItem.toString()
          naissanceUser = naissance.year.toString()+"-"+(naissance.month+1).toString()+"-"+naissance.dayOfMonth.toString()




            if(email.text.toString().isEmpty()){
                Email.error = "Email required"
                Email.requestFocus()
                return@setOnClickListener
            }

            if(!(professionUser.trim().equals("Professeur") || professionUser.trim().equals("Administrateur") || professionUser.trim().equals("Etudiant") )){
                text.text=" Veuillez choisir votre profession"
                return@setOnClickListener
            }

           var str_test : String="false"
            var str_nom : String =""
            var str_prenom : String =" "
            var str_existence : String =" "
            // 1 comment supprimé code volley

            val queue = Volley.newRequestQueue(this@ActiverCompte)
            val req = object : StringRequest(Request.Method.POST, url, Response1.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")
                    var str_user: String = ""



                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        str_user = str_user + "\n" + jsonInner.get("nom").toString() +"\n" +jsonInner.get("email").toString()
                        str_test =jsonInner.get("email").toString()
                       str_nom =jsonInner.get("nom").toString()
                        str_prenom = jsonInner.get("prenom").toString()
                        str_existence = jsonInner.get("existence").toString()

                    }


                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
            if(!str_test.trim().equals("") && str_existence.trim().equals("false")) {
                    val intent = Intent(applicationContext, ProfileActivation::class.java)
                intent.putExtra("email",str_test)
                intent.putExtra("nom",str_nom)
                intent.putExtra("prenom",str_prenom)
                intent.putExtra("profession",professionUser)

                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }

                if(str_existence.trim().equals("true")){

                text.text="vous avez déja activé votre compte"
            } else {

                text.text="vous n'etes pas un "+professionUser+" "

            }

            },object : Response1.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
var status : NetworkResponse
  Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
                    params.put("email",email.text.toString().trim())
                    params.put("profession",professionUser.trim())
                    params.put("naissance",naissanceUser)


                    return params
                }




            }



            queue.add(req)
            queue.start()











        }

       retour.setOnClickListener {

           val intent = Intent(applicationContext, MainActivity::class.java)
           intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


           startActivity(intent)



       }

    }






}
