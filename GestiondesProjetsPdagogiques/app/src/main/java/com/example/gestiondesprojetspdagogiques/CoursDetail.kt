package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
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

class CoursDetail : AppCompatActivity() {
    lateinit var listcours : ListView
    private val conf : config= config()
    private var Base :String= conf.Base
    lateinit var titre : TextView
    lateinit var back : LinearLayout
    private val url2: String = Base+"/projet-pedagogique/api/controller/watchCours.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cours_detail)
        titre = this.findViewById(R.id.titre)
        listcours = this.findViewById(R.id.listcours)
        back = this.findViewById(R.id.back)
        var Video_url=""
        var nom_cours = ""
        var id_cours = ""
        var listVideoUrl = ArrayList<String>()
        var listNomCours = ArrayList<String>()
        var listIdCours = ArrayList<String>()




      var id_et =   intent.getStringExtra("id")
      var filiere =   intent.getStringExtra("id_fil")
      var idmoduleChoisi =   intent.getStringExtra("id_module")
     var moduleChoisi =   intent.getStringExtra("module")

        /** Req Etudiant 2**/

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


                    listVideoUrl.add(jsonInner.get("url").toString())
                    listNomCours.add(jsonInner.get("nom").toString())
                    listIdCours.add(jsonInner.get("id").toString())

                }

                val adapter2 = android.widget.ArrayAdapter(this, android.R.layout.simple_list_item_1,listNomCours)
                listcours.adapter = adapter2

            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

            //


        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()

                params.put("id_module",idmoduleChoisi)



                return params
            }




        }



        queue.add(req)
        queue.start()



        /** Fin Req 2 **/







        listcours.setOnItemClickListener { parent, view, position, id ->

            for(i in 0 until listNomCours.size){

                if (position == i){
                    Video_url=listVideoUrl.get(position)
                    nom_cours = listNomCours.get(position)
                    id_cours = listIdCours.get(position)

                }
            }



            val intent = Intent(applicationContext,WatchCours::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("id",id_et)
            intent.putExtra("id_fil",filiere)
            intent.putExtra("id_module",idmoduleChoisi)
            intent.putExtra("module",moduleChoisi)
            intent.putExtra("vide_url",Video_url)
            intent.putExtra("nom_cours",nom_cours)
            intent.putExtra("id_cours",id_cours)

            startActivity(intent)




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
