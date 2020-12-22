package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Notification : AppCompatActivity() {

    lateinit var listeAccepter : ListView


    lateinit var btnBack : RelativeLayout

    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/Notification.php"
    private val url2: String = Base+"/projet-pedagogique/api/controller/NotificationOffre.php"
    private val url3: String = Base+"/projet-pedagogique/api/controller/ProjetTerminer.php"

    lateinit var  monProjet : CardView
    lateinit var monOffre : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        var id:String = intent.getStringExtra("id")

        var nom_du_prof = intent.getStringExtra("prof")
        var email_du_prof = intent.getStringExtra("email")
        var tele = intent.getStringExtra("tele")
        listeAccepter = this.findViewById(R.id.projetvalider)

        monProjet = this.findViewById(R.id.monprojet)
        monOffre = this.findViewById(R.id.offre)


        btnBack = this.findViewById(R.id.back)









        /** Button affiche projet activer **/

        monProjet.setOnClickListener {
val listv :ArrayList<String> = ArrayList<String>()
            val nombreSemestre : ArrayList <String> = ArrayList <String>()
            val listProjetById : ArrayList<String> = ArrayList<String>()

            val queue = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req = object : StringRequest(Request.Method.POST, url, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")




                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                       listv.add(jsonInner.get("nom_cycle").toString()+" "+jsonInner.get("nom_projet").toString())
                        listProjetById.add(jsonInner.get("id").toString())
                        nombreSemestre.add(jsonInner.get("nombre_semestre").toString())
                        //  departement = jsonInner.getString("departement").toString()

                    }

                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listv)
                listeAccepter.adapter = adapter

            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
                    params.put("id",id)
                    params.put("situation","oui")



                    return params
                }




            }



            queue.add(req)
            queue.start()





            listeAccepter.setOnItemClickListener { parent, view, position, id ->
                var id_projet =""
                var res1 =""
                var res2=""
                var egaux =""
                var nbr_semestre=""
                 //   var listEgaux = ArrayList<String>()
                for (i in 0 until listv.size) {

                    if (position == i) {

                        //   Toast.makeText(this, listProf.get(position).toString() + "qui a l id "+listProfbyId.get(position).toString(), Toast.LENGTH_SHORT).show()

                        id_projet = listProjetById.get(position)
                        nbr_semestre = nombreSemestre.get(position)

                        /** requete 3    **/

                        val queue3 = Volley.newRequestQueue(this)


                        // Request a string response from the provided URL.
                        val req3 = object : StringRequest(Request.Method.POST, url3, Response.Listener <String> {

                                response1  ->
                            try {

                                val obj = JSONObject(response1)
                                val jsonArray: JSONArray = obj.getJSONArray("data")




                                for (i in 0 until jsonArray.length()) {
                                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                                    res1 = jsonInner.get("res1").toString()
                                    res2=jsonInner.get("res2").toString()
                                   egaux = jsonInner.get("egaux").toString()
                                  //  listEgaux.add(jsonInner.get("egaux").toString())

                                }

                            } catch (e: JSONException){ e.printStackTrace()}
                            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin



if(egaux.equals("oui")){


    val intent = Intent(applicationContext, GenerationProjet::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    /* intent.putExtra("res1",res1)
     intent.putExtra("res2",res2)
   */ // intent.putExtra("diff",diff)

    intent.putExtra("id_projet",listProjetById.get(position))
    intent.putExtra("nombre_semestre",nbr_semestre)
    intent.putExtra("nom",nom_du_prof)
    intent.putExtra("email",email_du_prof)
    intent.putExtra("tele",tele)
    startActivity(intent)

}else{

    val intent = Intent(applicationContext, ProjetAttente::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra("res1",res1)
    intent.putExtra("res2",res2)
    // intent.putExtra("diff",diff)
    startActivity(intent)




}









                        },object : Response.ErrorListener {
                            override fun onErrorResponse(error: VolleyError?) {

                                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                            }
                        })   {

                            @Throws(AuthFailureError::class)
                            override fun getParams(): Map<String, String> {

                                val params = HashMap<String,String>()
                                params.put("id_projet",listProjetById.get(position))




                                return params
                            }




                        }



                        queue3.add(req3)
                        queue3.start()


                        /** fin requete 3 **/



                    }


                }









            }















        }

        /** Button afficher projet en attente **/



        monOffre.setOnClickListener {

            val listv: ArrayList<String> = ArrayList<String>()
            val listModuleById: ArrayList<String> = ArrayList<String>()
            val listModuleByNom: ArrayList<String> = ArrayList<String>()
            val listProjetByNom: ArrayList<String> = ArrayList<String>()
            val listProjetById : ArrayList<String> = ArrayList<String>()
            val listNomSemestre : ArrayList<String> = ArrayList<String>()
            val nombreSemestre : ArrayList <String> = ArrayList <String>()

            val queue = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> {

                    response1 ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")




                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                        listv.add("Recu par Mr." + " " + jsonInner.get("ProfNom").toString() + " " + jsonInner.get("ProfPrenom").toString())
                        listModuleById.add(jsonInner.get("id_module").toString())
                        listModuleByNom.add(jsonInner.get("module").toString())
                        listProjetByNom.add(jsonInner.get("cycle").toString()+" "+jsonInner.get("nom_projet").toString())
                        listProjetById.add(jsonInner.get("id_projet").toString())
                        listNomSemestre.add(jsonInner.get("semestre").toString())

                        //  departement = jsonInner.getString("departement").toString()

                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listv)
                listeAccepter.adapter = adapter

            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String, String>()
                    params.put("id", id)



                    return params
                }


            }



            queue.add(req)
            queue.start()



            listeAccepter.setOnItemClickListener { parent, view, position, id ->

                for (i in 0 until listv.size) {

                    if (position == i) {

                        //   Toast.makeText(this, listProf.get(position).toString() + "qui a l id "+listProfbyId.get(position).toString(), Toast.LENGTH_SHORT).show()
                        var id_module = listModuleById.get(position)
                        var projetNom = listProjetByNom.get(position)
                        var module_intitule = listModuleByNom.get(position)
                        var id_projet = listProjetById.get(position)
                        var nom_semestre = listNomSemestre.get(position)


                        val intent = Intent(applicationContext, ModuleDetail::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("id_module", id_module)
                        intent.putExtra("intitule", module_intitule)
                        intent.putExtra("projetNom", projetNom)
                        intent.putExtra("id_projet",id_projet)
                        intent.putExtra("semestre",nom_semestre)
                        intent.putExtra("prof",nom_du_prof)
                        startActivity(intent)


                    }


                }


            }


        }

        /** Button afficher projet Refuser **/



        btnBack.setOnClickListener {


            val intent = Intent(applicationContext, ProfileActivity::class.java)
            /* intent.putExtra("email",str_test)
             intent.putExtra("nom",str_nom)
             intent.putExtra("prenom",str_prenom)
             intent.putExtra("profession",professionUser) */

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)



        }







    }
}
