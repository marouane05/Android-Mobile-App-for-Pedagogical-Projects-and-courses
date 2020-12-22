package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_generation_projet.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class AjoutCours : AppCompatActivity() {


    val conf: config = config()
    lateinit var session: SessionManager

    var Base: String = conf.Base
    lateinit var back : LinearLayout
    val url1 = Base + "/projet-pedagogique/api/controller/ListFiliereProf.php"
    val url2 = Base + "/projet-pedagogique/api/controller/ListCourses.php"
    val url3 = Base + "/projet-pedagogique/api/controller/addCoursTest.php"

    lateinit var choixfilier: Button

    lateinit var choixcours: Button
    lateinit var intituleCours: EditText
    lateinit var terminer: Button
    lateinit var nomfiliere: TextView
    lateinit var courschoisi : TextView
    private lateinit var myDialog: AlertDialog
    var ListFiliere: ArrayList<String> = ArrayList<String>()
    var ListFiliereById: ArrayList<String> = ArrayList<String>()
    var ListModuleById: ArrayList<String> = ArrayList<String>()

    var ChangeListFiliere: ArrayList<String> = ArrayList<String>()
    var ChangeListFiliereById: ArrayList<String> = ArrayList<String>()
    var ChangeListModuleById: ArrayList<String> = ArrayList<String>()



    var ListCoursById: ArrayList<String> = ArrayList<String>()
    var ListCoursByNom: ArrayList<String> = ArrayList<String>()
    var ListModule: ArrayList<String> = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_cours)
        back = this.findViewById(R.id.back)
        courschoisi = this.findViewById(R.id.courschoisi)
        choixfilier = this.findViewById(R.id.choixfiliere)
        choixcours = this.findViewById(R.id.choixcours)

        nomfiliere = this.findViewById(R.id.nomfiliere)
        intituleCours = this.findViewById(R.id.intitule)
        terminer = this.findViewById(R.id.terminer)



        nomfiliere.visibility = View.INVISIBLE
        courschoisi.visibility = View.INVISIBLE


        var id_prof = intent.getStringExtra("id")
        var MonFiliere = ""
        var MonModule = ""
        var MonCours = ""


        back.setOnClickListener {



                val intent = Intent(applicationContext, ProfileActivity::class.java)
                /* intent.putExtra("email",str_test)
                 intent.putExtra("nom",str_nom)
                 intent.putExtra("prenom",str_prenom)
                 intent.putExtra("profession",professionUser) */

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)

        }






        /** Debut de requete 1 **/
        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req = object : StringRequest(Request.Method.POST, url1, Response.Listener<String> {

                response1 ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")


                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    // cycle = jsonInner.get("nom_cycle").toString()
                    //date = jsonInner.get("date_demande").toString()

                    ListFiliere.add(jsonInner.get("moduleintitule").toString() + " (" + jsonInner.get("intitule").toString() + ")")
                    ListFiliereById.add(jsonInner.get("id").toString())
                    ListModuleById.add(jsonInner.get("idmodule").toString())
                    ListModule.add(jsonInner.get("moduleintitule").toString())


                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){

    Toast.makeText(this, listProf.get(i), Toast.LENGTH_LONG).show()
} */


        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {


                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        }) {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String, String>()
                params.put("id_prof", id_prof)



                return params
            }


        }



        queue.add(req)
        queue.start()


        /** fin requete 1 **/














        choixfilier.setOnClickListener {



    val myBuilder = AlertDialog.Builder(this)
    //DATA SOURCE
    //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
    //SET PROPERTIES USING METHOD CHAINING
    val arr =  ListFiliere.toTypedArray()
    val arrId = ListFiliereById.toTypedArray()
    val modId = ListModuleById.toTypedArray()

    myBuilder.setTitle("Choisir module").setItems(arr) { dialogInterface, position ->

        MonFiliere = arrId[position].toString()
        MonModule = modId[position].toString()
nomfiliere.text = MonModule + "("+ MonModule+")"
        Toast.makeText(this, arr[position].toString() + arrId[position].toString(), Toast.LENGTH_SHORT).show()
    }
    //CREATE DIALOG


    myDialog = myBuilder.create()
    //SHOW DIALOG
    myDialog.show()



        }








        /** Debut de requete 1 **/
        val queue2 = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req2 = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> {

                response1 ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")


                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    // cycle = jsonInner.get("nom_cycle").toString()
                    //date = jsonInner.get("date_demande").toString()

                    ListCoursById.add(jsonInner.get("id").toString())
                    ListCoursByNom.add(jsonInner.get("nom").toString())


                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){

    Toast.makeText(this, listProf.get(i), Toast.LENGTH_LONG).show()
} */


        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {


                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        }) {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String, String>()




                return params
            }


        }



        queue2.add(req2)
        queue2.start()


        /** fin requete 1 **/








        choixcours.setOnClickListener {









            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = ListCoursByNom.toTypedArray()
            val arrId = ListCoursById.toTypedArray()


            myBuilder.setTitle("Choisir module").setItems(arr) { dialogInterface, position ->

                MonCours = arrId[position].toString()

            courschoisi.text=MonCours
                Toast.makeText(this, arr[position].toString() + arrId[position].toString(), Toast.LENGTH_SHORT).show()
            }
            //CREATE DIALOG


            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()




        }




        terminer.setOnClickListener {
            Toast.makeText(this, MonModule + "et cours" + MonCours, Toast.LENGTH_SHORT).show()


            /** Debut de requete 1 **/
            val queue3 = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req3 = object : StringRequest(Request.Method.POST, url3, Response.Listener<String> {

                    response1 ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")


                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                        // cycle = jsonInner.get("nom_cycle").toString()
                        //date = jsonInner.get("date_demande").toString()


                    }


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){

    Toast.makeText(this, listProf.get(i), Toast.LENGTH_LONG).show()
} */


            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {


                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            }) {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String, String>()
                    params.put("module", MonModule)
                    params.put("cours", MonCours)
                    params.put("titre", intituleCours.text.toString())


                    return params
                }


            }



            queue3.add(req3)
            queue3.start()


            /** fin requete 1 **/


        }


    }

}