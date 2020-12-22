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
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import androidx.core.view.get


class ValidProjet : AppCompatActivity() {

    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/detailProjet.php"
    lateinit var  voir : Button
    lateinit var list : ListView
    lateinit var session : SessionManager
    lateinit var back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valid_projet)


        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()

        var nom : String = ""
        back = this.findViewById(R.id.back)

        list = this.findViewById(R.id.list)


        var listProf : ArrayList<String> = ArrayList<String>()
        var listProfbyId : ArrayList<String> = ArrayList<String>()

        var listCycle : ArrayList<String> = ArrayList<String>()

        /** commence requet **/

        var id_projet : String =""


            val queue = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req = object : StringRequest(Request.Method.POST, url, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")
                    var str_user: String = ""
                    var cycle : String=""
                    var date : String=""

                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                        cycle = jsonInner.get("nom_cycle").toString()
                        //date = jsonInner.get("date_demande").toString()

                        listProf.add(cycle +" "+jsonInner.get("nom_projet").toString())
                        listProfbyId.add(jsonInner.get("id_projet").toString())
listCycle.add(jsonInner.get("nom_cycle").toString())

                    }





                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){

    Toast.makeText(this, listProf.get(i), Toast.LENGTH_LONG).show()
} */

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listProf)
list.adapter = adapter



            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,nom, Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()




                    return params
                }




            }



            queue.add(req)
            queue.start()



     /*   list.setOnItemClickListener(object : AdapterView.OnItemClickListener {


                // Get the selected item text from ListView
                //val selectedItem = parent.getItemAtPosition(position) as String


            }
   */

        list.setOnItemClickListener { parent, view, position, id ->

for (i in 0 until listProf.size) {

    if(position== i){

     //   Toast.makeText(this, listProf.get(position).toString() + "qui a l id "+listProfbyId.get(position).toString(), Toast.LENGTH_SHORT).show()
   id_projet = listProfbyId.get(position)
   var le_cycle = listCycle.get(position)
        val intent = Intent(applicationContext, DetailProjet::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
intent.putExtra("id",id_projet)
        intent.putExtra("cycle",le_cycle)
        startActivity(intent)






    }


}



        }





        back.setOnClickListener {



                val intent = Intent(applicationContext, ProfileAdmin::class.java)
                /* intent.putExtra("email",str_test)
                 intent.putExtra("nom",str_nom)
                 intent.putExtra("prenom",str_prenom)
                 intent.putExtra("profession",professionUser) */

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)




        }








        }



/** fin requete **/









































}




