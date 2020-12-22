package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ppedagogique.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.widget.Spinner as Spinner2

class Ppedagogique : AppCompatActivity() {


    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/addProjet.php"

    lateinit var back : LinearLayout
    lateinit var session : SessionManager

    lateinit var suivant : Button
    lateinit var cycleSpinner : Spinner2
    lateinit var cout : EditText
    lateinit var checkbox_formation : CheckBox
    lateinit var nom : EditText
    lateinit var descriptions : EditText
    lateinit var admission : EditText
    lateinit var debouche : EditText
    lateinit var coordinateur : EditText
    lateinit var typeFormation : LinearLayout
    lateinit var  layoutcout : RelativeLayout
    lateinit var semestenb : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ppedagogique)

        suivant = this.findViewById(R.id.suivant)
        cycleSpinner = this.findViewById(R.id.cycle)
        cout = this.findViewById(R.id.cout)
        checkbox_formation= this.findViewById(R.id.checkbox_formation)
        nom = this.findViewById(R.id.nom)
        descriptions=this.findViewById(R.id.description)
        admission = this.findViewById(R.id.admission)
        debouche = this.findViewById(R.id.debouches)
        semestenb = this.findViewById(R.id.semestenb)

var coutnoter = " "

       var id_projet :String =""
        var  id_cycle : Int = 1
      var id:String = intent.getStringExtra("id")
        back=this.findViewById(R.id.back)

        session = SessionManager(applicationContext)
        session.checkLogin()

        layoutcout= this.findViewById(R.id.layoutcout)


        ArrayAdapter.createFromResource(
            this,
            R.array.cycle,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            cycleSpinner.adapter = adapter
        }









        layoutcout.visibility = View.INVISIBLE;
      //  var choisi :String = cycleSpinner.selectedItem.toString()




        checkbox_formation.setOnClickListener {

                layoutcout.visibility = View.VISIBLE;
            if(!checkbox_formation.isChecked){
                layoutcout.visibility = View.INVISIBLE;
            }

          

  }

        back.setOnClickListener {

         //   session.LogoutUser()




                val intent = Intent(applicationContext, ProfileActivity::class.java)
                /* intent.putExtra("email",str_test)
                 intent.putExtra("nom",str_nom)
                 intent.putExtra("prenom",str_prenom)
                 intent.putExtra("profession",professionUser) */

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)



            }





        suivant.setOnClickListener {

coutnoter = cout.text.toString()

            if(cycle.selectedItem.toString().trim().equals("DUT")){
                id_cycle=1

            }
            if(cycle.selectedItem.toString().trim().equals("Lp")){
                id_cycle=2
            }


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

                        id_projet =jsonInner.get("id_projet").toString()



                    }




                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

                var desc : String = description.text.toString()


                    val intent = Intent(applicationContext, AffecteModule::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              //      var nbsem = semestenb.text.toString().toInt()

                    intent.putExtra("id",id_projet)
                    intent.putExtra("nom",nom.text.toString())
                    intent.putExtra("desc",desc)
                    intent.putExtra("admission",admission.text.toString())
                    intent.putExtra("debouche",debouche.text.toString())
                    intent.putExtra("cout",coutnoter)
                    intent.putExtra("nbr_semestre",semestenb.text.toString())
                    intent.putExtra("departSemestre","1")

                    startActivity(intent)





            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
var newcout = "0"
                    if(cout.text.toString().equals("")){
                        newcout = "10"
                    } else {
                        newcout =cout.text.toString()
                    }

                    val params = HashMap<String,String>()
                    params.put("nom_projet",nom.text.toString())
                    params.put("admission",admission.text.toString())
                    params.put("debouche",debouche.text.toString())
                    params.put("description",description.text.toString())
                    params.put("cout",newcout)
                    params.put("id_responsable",id)
                    params.put("id_cycle",id_cycle.toString())
                    params.put("nbr_semestre",semestenb.text.toString())

                    return params
                }


            }



            queue.add(req)
            queue.start()


















        }


    }

    }









