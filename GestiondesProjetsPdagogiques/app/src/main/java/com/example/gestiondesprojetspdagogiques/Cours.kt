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
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Cours : AppCompatActivity() {



    lateinit var titre : TextView
    lateinit var list : ListView
     lateinit var back : LinearLayout
    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/getModuleByFil.php"

    lateinit var session : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cours)

        titre = this.findViewById(R.id.titre)
        list = this.findViewById(R.id.list)
      back = this.findViewById(R.id.back)

  var filiere = intent.getStringExtra("id_fil")
  var id_et = intent.getStringExtra("id")

   var idmoduleChoisi  = ""
   var moduleChoisi = ""

var CoursList = ArrayList<String>()
var CoursListById = ArrayList<String>()


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

                        CoursList.add(jsonInner.get("intitule").toString()+" Semestre"+jsonInner.get("id_semestre").toString())
                        CoursListById.add(jsonInner.get("id").toString())


                    }



                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
                Toast.makeText(applicationContext,"second "+filiere, Toast.LENGTH_LONG).show()
                //
                val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_list_item_1,CoursList)
                list.adapter = adapter


            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()

                    params.put("id_fil",filiere)



                    return params
                }




            }



            queue.add(req)
            queue.start()







        var Video_url=""
        var nom_cours = ""
        var id_cours = ""
        var listVideoUrl = ArrayList<String>()
        var listNomCours = ArrayList<String>()
        var listIdCours = ArrayList<String>()





        list.setOnItemClickListener { parent, view, position, id ->





            for(i in 0 until CoursList.size){

                if (position == i){

                    idmoduleChoisi  = CoursListById.get(position)
                    moduleChoisi = CoursList.get(position)

                    val intent = Intent(applicationContext,CoursDetail::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("id",id_et)
                    intent.putExtra("id_fil",filiere)
                    intent.putExtra("id_module",idmoduleChoisi)
                    intent.putExtra("module",moduleChoisi)


                    startActivity(intent)

                }
            }







        }

        back.setOnClickListener {



            val intent = Intent(applicationContext, ProfileEtudiant::class.java)
            /* intent.putExtra("email",str_test)
             intent.putExtra("nom",str_nom)
             intent.putExtra("prenom",str_prenom)
             intent.putExtra("profession",professionUser) */

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

        }





    }
}
