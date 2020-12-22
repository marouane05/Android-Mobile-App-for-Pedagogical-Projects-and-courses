package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class ModuleDetail : AppCompatActivity() {
    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/RemplirModule.php"
    private val url2: String = Base+"/projet-pedagogique/api/controller/RemplirElement.php"

    lateinit var elem1nom : EditText
    lateinit var elem2nom : EditText
    lateinit var elem3nom : EditText

    lateinit var helem1tp : EditText
    lateinit var helem2tp : EditText
    lateinit var helem3tp : EditText

    lateinit var helem1td : EditText
    lateinit var helem2td : EditText
    lateinit var helem3td : EditText

    lateinit var helem1cour : EditText
    lateinit var helem2cour : EditText
    lateinit var helem3cour : EditText

    lateinit var elem1desc : EditText
    lateinit var elem2desc : EditText
    lateinit var elem3desc : EditText

    lateinit var evaluation : EditText
    lateinit var note : EditText
    lateinit var validation : EditText
    lateinit var objectifs : EditText
    lateinit var  prerequis : EditText

    lateinit var intitule : TextView
    lateinit var projet : TextView

    lateinit var terminer : Button
   lateinit var back : RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_detail)

        elem1nom =this.findViewById(R.id.elem1nom)
        elem2nom =this.findViewById(R.id.elem2nom)
        elem3nom =this.findViewById(R.id.elem3nom)

        helem1tp = this.findViewById(R.id.helem1tp)
        helem2tp = this.findViewById(R.id.helem2tp)
        helem3tp = this.findViewById(R.id.helem3tp)

        helem1td = this.findViewById(R.id.helem1tp)
        helem2td = this.findViewById(R.id.helem2tp)
        helem3td = this.findViewById(R.id.helem3tp)

        helem1cour = this.findViewById(R.id.helem1cours)
        helem2cour = this.findViewById(R.id.helem2cours)
        helem3cour = this.findViewById(R.id.helem3cours)

        evaluation = this.findViewById(R.id.evaluation)
        note = this.findViewById(R.id.note)
        validation=this.findViewById(R.id.validation)
        objectifs= this.findViewById(R.id.objectifs)
        prerequis = this.findViewById(R.id.prerequis)

        intitule = this.findViewById(R.id.intitule)
       // projet = this.findViewById(R.id.projet)
        terminer = this.findViewById(R.id.terminer)

        elem1desc = this.findViewById(R.id.elem1desc)
        elem2desc = this.findViewById(R.id.elem2desc)
        elem3desc = this.findViewById(R.id.elem3desc)

back = this.findViewById(R.id.back)


        var id_module :String = intent.getStringExtra("id_module")
        var intitule_module :String = intent.getStringExtra("intitule")
        var projet_nom :String = intent.getStringExtra("projetNom")
        var id_projet = intent.getStringExtra("id_projet")

        var prof = intent.getStringExtra("prof")
        var semestre = intent.getStringExtra("semestre")

//        projet.setText(projet_nom)
        intitule.setText("Projet: "+projet_nom+"-"+" Module: "+intitule_module)

        var listElementBynom = ArrayList<String>()
        var listHourByTd = ArrayList<String>()
        var listHourByCour = ArrayList<String>()
        var listHourByTp = ArrayList<String>()
        var listElemDesc = ArrayList<String>()


        terminer.setOnClickListener {

            listElementBynom.add(0,elem1nom.text.toString())
            listElementBynom.add(1,elem2nom.text.toString())
            listElementBynom.add(2,elem3nom.text.toString())

            listHourByTd.add(0,helem1td.text.toString()+" h")
            listHourByTd.add(1,helem2td.text.toString()+" h")
            listHourByTd.add(2,helem3td.text.toString()+" h")

            listHourByCour.add(0,helem1cour.text.toString()+" h")
            listHourByCour.add(1,helem2cour.text.toString()+" h")
            listHourByCour.add(2,helem3cour.text.toString()+" h")

            listHourByTp.add(0,helem1tp.text.toString()+" h")
            listHourByTp.add(1,helem2tp.text.toString()+" h")
            listHourByTp.add(2,helem3tp.text.toString()+" h")

            listElemDesc.add(0,elem1desc.text.toString())
            listElemDesc.add(1,elem2desc.text.toString())
            listElemDesc.add(2,elem3desc.text.toString())





            /** module 1 **/

            val queue = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req = object : StringRequest(Request.Method.POST, url, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")




                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)



                        //  departement = jsonInner.getString("departement").toString()

                    }

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
                    params.put("id_module",id_module)
                    params.put("objectif",objectifs.text.toString())
                    params.put("prerequis",prerequis.text.toString())
                    params.put("modeevaluation",evaluation.text.toString())
                    params.put("notemodule",note.text.toString())
                    params.put("validation",validation.text.toString())
                    params.put("id_projet",id_projet)



                    return params
                }




            }



            queue.add(req)
            queue.start()




            /** fin req1   **/






            /** module 1 **/

            val queue2 = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req2 = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")




                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)



                        //  departement = jsonInner.getString("departement").toString()

                    }

                } catch (e: JSONException){ e.printStackTrace()}
                // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

               /* val intent = Intent(applicationContext, RemerciementProf::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
*/



                val intent = Intent(applicationContext, DescriptifModule::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                // intent.putExtra("diff",diff)

                intent.putExtra("id_module", id_module)
                intent.putExtra("mintitule",intitule_module)
                intent.putExtra("objectif",objectifs.text.toString())
                intent.putExtra("modeevaluation",evaluation.text.toString())
                intent.putExtra("notemodule",note.text.toString())
                intent.putExtra("validation",validation.text.toString())
                intent.putExtra("id_projet",id_projet)
                intent.putExtra("prerequis", prerequis.text.toString())
                intent.putExtra("elem1",  listElementBynom.get(0))
                intent.putExtra("elem2",  listElementBynom.get(1))
                intent.putExtra("elem3",  listElementBynom.get(2))
                intent.putExtra("hrtd1",listHourByTd.get(0))
                intent.putExtra("hrtd2",listHourByTd.get(1))
                intent.putExtra("hrtd3",listHourByTd.get(2))
                intent.putExtra("hrcours1",listHourByCour.get(0))
                intent.putExtra("hrcours2",listHourByCour.get(1))
                intent.putExtra("hrcours3",listHourByCour.get(2))
                intent.putExtra("hrtp1",listHourByTp.get(0))
                intent.putExtra("hrtp2",listHourByTp.get(1))
                intent.putExtra("hrtp3",listHourByTp.get(2))
                intent.putExtra("elemdesc1", listElemDesc.get(0))
                intent.putExtra("elemdesc2", listElemDesc.get(1))
                intent.putExtra("elemdesc3", listElemDesc.get(2))
                intent.putExtra("prof",prof)
                intent.putExtra("semestre",semestre)





                startActivity(intent)




            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
                    for (i in 0 until listElementBynom.size) {
                        params.put("id_module", id_module)
                        params.put("desc", listElemDesc.get(i))
                        params.put("intitule", listElementBynom.get(i))
                        params.put("hrtd", listHourByTd.get(i))
                        params.put("hrcours", listHourByCour.get(i))
                        params.put("hrtp", listHourByTp.get(i))


                    }



                    return params
                }




            }



            queue2.add(req2)
            queue.start()




            /** fin req1   **/
































        }



        back.setOnClickListener {

            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // intent.putExtra("diff",diff)
            startActivity(intent)








        }



    }
}
