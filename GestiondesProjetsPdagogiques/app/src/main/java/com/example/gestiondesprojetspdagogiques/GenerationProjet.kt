package com.example.gestiondesprojetspdagogiques

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gestiondesprojetspdagogiques.dummy.*
import kotlinx.android.synthetic.main.activity_generation_projet.*
import kotlinx.android.synthetic.main.activity_module_detail.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.widget.ArrayAdapter as ArrayAdapter1
import android.widget.ExpandableListAdapter as ExpandableListAdapter1
import android.widget.Toast
import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.util.DisplayMetrics
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import com.android.volley.*
import com.android.volley.toolbox.ImageLoader
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


class GenerationProjet : AppCompatActivity() {

    private var projetintitule =""
    private lateinit var bitmap0 : Bitmap
    private  lateinit var bitmap1 : Bitmap
    private lateinit var bitmap2 : Bitmap
    private lateinit var bitmap3: Bitmap
    private  lateinit var bitmap4 : Bitmap
    private lateinit var bitmap5 : Bitmap
    private lateinit var bitmap6 : Bitmap
    private  lateinit var bitmap7 : Bitmap
    private lateinit var bitmap8 : Bitmap
    private lateinit var bitmap9 : Bitmap
    private lateinit var tst : Button
    private lateinit var myDialog: AlertDialog
    private  lateinit var param : RelativeLayout
   private lateinit var back : RelativeLayout
    private var listBitmapPage1 : ArrayList<String> = ArrayList<String>()
   private var listBitmapPage2 : ArrayList<Bitmap> = ArrayList<Bitmap>()
    private var listBitmapbm : ArrayList<Bitmap> = ArrayList<Bitmap>()


    lateinit var  listexp : ExpandableListView
    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()


        lateinit var session : SessionManager


        val conf : config=config()
        var Base :String= conf.Base

        val url1 = Base+"/projet-pedagogique/api/controller/ProjetPrecis.php"
        val url2 = Base+"/projet-pedagogique/api/controller/ListModuleBySemestre.php"
        val url3 = Base+"/projet-pedagogique/api/controller/getModule.php"
        val urlprof = Base+"/projet-pedagogique/api/controller/ListProfByProjet.php"
       val urlFile = Base+"/projet-pedagogique/api/controller/diplayFile.php"


    val url4 = Base+"/projet-pedagogique/api/controller/listModuleByProject.php"
        lateinit var esteimage :ImageView
        lateinit var  intituleprojet : TextView
        lateinit var  notrepagee : RelativeLayout
        lateinit var gendesc : TextView
        lateinit var genadmission : TextView
        lateinit var gendebouches : TextView
        lateinit var listProf : TextView
      //  lateinit var recylclerModule : RecyclerView
        lateinit var objmodule : TextView
        lateinit var prerequismodule : TextView
        lateinit var llPdf : FrameLayout
        lateinit var nomelement1 : TextView
        lateinit var descelement1 : TextView
        lateinit var nomelement2 : TextView
        lateinit var descelement2 : TextView
        lateinit var nomelement3 : TextView
        lateinit var descelement3 : TextView




    lateinit var listviewequipe : ListView
    lateinit var listviewprogramme : ListView
       lateinit var  listviewAdapter : ExpandableListAdapter
//lateinit var  recylclerElement : RecyclerView
    lateinit var noteval : TextView
    lateinit var notemod : TextView
    lateinit var notevalidation : TextView

    lateinit var  listModule : ListView

    lateinit var case : TextView


    lateinit var numcord : TextView
    lateinit var nomcord : TextView
    lateinit var emailcord : TextView



    lateinit var  page1 :LinearLayout
    lateinit var page0 : LinearLayout
    lateinit var page2 : LinearLayout
    lateinit var page3 : LinearLayout
    lateinit var pageequipe : LinearLayout
    lateinit var  page4 :LinearLayout
    lateinit var page5 : LinearLayout
    lateinit var page6 : LinearLayout
    lateinit var page7 : LinearLayout






        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generation_projet)

          //  val inflatedView = layoutInflater.inflate(R.layout.modules, null)



 var id_projet = intent.getStringExtra("id_projet")
 var nombreSemestre =intent.getStringExtra("nombre_semestre")


 var email = intent.getStringExtra("email")
 var nom = intent.getStringExtra("nom")
var tele = intent.getStringExtra("tele")





            listviewequipe = this.findViewById(R.id.listviewequipe)

back = this.findViewById(R.id.back)
gendesc = this.findViewById(R.id.gendesc)
genadmission = this.findViewById(R.id.genadmission)
gendebouches = this.findViewById(R.id.gendebouches)

numcord = this.findViewById(R.id.numcord)
nomcord = this.findViewById(R.id.nomcord)
emailcord = this.findViewById(R.id.emailcord)

            nomcord.setText(nom)
            numcord.setText("0"+tele)
            emailcord.setText(email)



 listviewprogramme = this.findViewById(R.id.listviewprogramme)

      param = this.findViewById(R.id.param)
        //    llPdf=this.findViewById(R.id.monFile)


        //    recylclerModule.layoutManager = LinearLayoutManager(this, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)

        //    recylclerElement = inflatedView.findViewById(R.id.RecyclerViewElement)
       //     recylclerElement.layoutManager = LinearLayoutManager(this, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)

page0 = this.findViewById(R.id.pagezero)
page1 = this.findViewById(R.id.pageun)
page2 = this.findViewById(R.id.pagedeux)
page3 = this.findViewById(R.id.pagetroix)
pageequipe = this.findViewById(R.id.pageequipe)
page4 = this.findViewById(R.id.pagequatre)
page5 = this.findViewById(R.id.pagecinq)
page6 = this.findViewById(R.id.pagesix)
page7 = this.findViewById(R.id.pagecept)




            /*
objmodule = this.findViewById(R.id.objmodule)
prerequismodule = this.findViewById(R.id.prerequismodule)
nomelement1 = this.findViewById(R.id.nomelement1)
descelement1 = this.findViewById(R.id.descelem1)
nomelement2 = this.findViewById(R.id.nomelement2)
descelement2 = this.findViewById(R.id.descelem2)
nomelement3 = this.findViewById(R.id.nomelement3)
descelement3 = this.findViewById(R.id.descelem3)

*/
intituleprojet = this.findViewById(R.id.intituleprojet)

            /*
noteval = this.findViewById(R.id.noteval)
notemod = this.findViewById(R.id.notemod)
notevalidation = this.findViewById(R.id.notevalidation)
*/

var projetdescription =" "
 var projetadmission =" "
 var projetdebouches =" "









            /** Req 1 **/

          val  ListNomEquipePedagogique : ArrayList<String> = ArrayList<String>()
            val  ListEmailEquipePedagogique : ArrayList<String> = ArrayList<String>()
            val  ListTeleEquipePedagogique : ArrayList<String> = ArrayList<String>()






    val queueProf = Volley.newRequestQueue(this)


    // Request a string response from the provided URL.
    val reqProf = object : StringRequest(Request.Method.POST, urlprof, Response.Listener<String> {

            response1 ->
        try {

            val obj = JSONObject(response1)
            val jsonArray: JSONArray = obj.getJSONArray("data")




            for (i in 0 until jsonArray.length()) {
                var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                ListNomEquipePedagogique.add("Pr. " + jsonInner.get("nom").toString() + " " + jsonInner.get("prenom"))
                ListTeleEquipePedagogique.add("0"+jsonInner.get("tele").toString())
                ListEmailEquipePedagogique.add(jsonInner.get("email").toString())


            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
        val nom: Array<String> = ListNomEquipePedagogique.toTypedArray()
        val tele: Array<String> = ListTeleEquipePedagogique.toTypedArray()
        val email: Array<String> = ListEmailEquipePedagogique.toTypedArray()


        Log.d("size list", ListNomEquipePedagogique.size.toString())
        val myListAdapter = MyListAdapter(this, nom, email, tele)



        listviewequipe.adapter = myListAdapter

    }, object : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {

            Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
        }
    }) {

        @Throws(AuthFailureError::class)
        override fun getParams(): Map<String, String> {

            val params = HashMap<String, String>()
            params.put("id_projet",id_projet)



            return params
        }


    }



    queueProf.add(reqProf)
    queueProf.start()
















            /** Fin Req 1 **/





















            /** Req 1 **/


                val queue = Volley.newRequestQueue(this)


                // Request a string response from the provided URL.
                val req = object : StringRequest(Request.Method.POST, url1, Response.Listener <String> {

                                response1  ->
                        try {

                                val obj = JSONObject(response1)
                                val jsonArray: JSONArray = obj.getJSONArray("data")




                                for (i in 0 until jsonArray.length()) {
                                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                                        projetdescription =jsonInner.get("description").toString()
                                        projetadmission =jsonInner.get("admission").toString()
                                        projetdebouches =jsonInner.get("debouche").toString()
                                        projetintitule =jsonInner.get("nom_cycle").toString()+" "+jsonInner.get("nom_projet").toString()


                                        //  departement = jsonInner.getString("departement").toString()

                                }

                        } catch (e: JSONException){ e.printStackTrace()}
                        // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
intituleprojet.setText(projetintitule)
genadmission.setText(projetadmission)
gendebouches.setText(projetdebouches)
gendesc.setText(projetdescription)

                },object : Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError?) {

                                Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                        }
                })   {

                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {

                                val params = HashMap<String,String>()
                                params.put("id",id_projet)



                                return params
                        }




                }



                queue.add(req)
                queue.start()


















                /** Fin Req 1 **/






        title = "Game Of Thrones"





            val semestre1: MutableList<String> = ArrayList()
            val semestre2: MutableList<String> = ArrayList()
            val semestre3: MutableList<String> = ArrayList()
            val semestre4: MutableList<String> = ArrayList()
            val semestre5: MutableList<String> = ArrayList()
            val semestre6: MutableList<String> = ArrayList()
            val semestre7: MutableList<String> = ArrayList()
            val semestre8: MutableList<String> = ArrayList()

            var maxId = ArrayList<String>()
            var ListDesModules = ArrayList<String>()
            var ListDesModulesById = ArrayList<String>()


            var module1 : String = ""
            var module2 : String = ""
            var module3 : String = ""
            var module4 : String = ""
            var module5 : String = ""
            var module6 : String = ""
            var module7 : String = ""
            var module8 : String = ""


            /** req 2 **/

            val modules : Array<String>
            val semestres : Array<String>
            val queue2 = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req2 = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")




                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                        if(jsonInner.get("id_semestre").toString().equals("1")){

                            semestre1.add(jsonInner.get("nom_module").toString())
                         module1 = module1 +"\n"+jsonInner.get("nom_module").toString()

                        }

                        if(jsonInner.get("id_semestre").toString().equals("2")){

                            semestre2.add(jsonInner.get("nom_module").toString())

                            module2 = module2 +"\n"+jsonInner.get("nom_module").toString()
                        }

                        if(jsonInner.get("id_semestre").toString().equals("3")){

                            semestre3.add(jsonInner.get("nom_module").toString())
                            module3 = module3 +"\n"+jsonInner.get("nom_module").toString()

                        }


                        if(jsonInner.get("id_semestre").toString().equals("4")){

                            semestre4.add(jsonInner.get("nom_module").toString())
                            module4 = module4 +"\n"+jsonInner.get("nom_module").toString()

                        }


                        if(jsonInner.get("id_semestre").toString().equals("5")){

                            semestre5.add(jsonInner.get("nom_module").toString())
                            module5 = module5 +"\n"+jsonInner.get("nom_module").toString()

                        }


                        if(jsonInner.get("id_semestre").toString().equals("6")){

                            semestre6.add(jsonInner.get("nom_module").toString())
                            module6 = module6 +"\n"+jsonInner.get("nom_module").toString()

                        }
                        if(jsonInner.get("id_semestre").toString().equals("7")){

                            semestre7.add(jsonInner.get("nom_module").toString())
                            module7 = module7 +"\n"+jsonInner.get("nom_module").toString()

                        }
                        if(jsonInner.get("id_semestre").toString().equals("8")){

                            semestre8.add(jsonInner.get("nom_module").toString())
                            module8 = module8 +"\n"+jsonInner.get("nom_module").toString()

                        }


maxId.add(jsonInner.get("id_semestre").toString())

                        //  departement = jsonInner.getString("departement").toString()
ListDesModules.add(jsonInner.get("nom_module").toString())
ListDesModulesById.add(jsonInner.get("id").toString())


                    }

                } catch (e: JSONException){ e.printStackTrace()}
                /*        val modules: Array<String> = arrayOf(module1,module2,module3,module4,module5,module6,module7,module8)
                           val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4","semestre 5","semestre 6","semestre 7","semestre 8")

               */


if(nombreSemestre.equals("1")){
    val modules: Array<String> = arrayOf(module1)
    val semestres: Array<String> = arrayOf("semestre 1")

    Log.d("size list", ListNomEquipePedagogique.size.toString())
    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
    listviewprogramme.adapter = myListProgrammeAdapter
}

if(nombreSemestre.equals("2")){
    val modules: Array<String> = arrayOf(module1,module2)
    val semestres: Array<String> = arrayOf("semestre 1","semestre 2")

    Log.d("size list", ListNomEquipePedagogique.size.toString())
    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
    listviewprogramme.adapter = myListProgrammeAdapter
}


   if(nombreSemestre.equals("3")){

       val modules: Array<String> = arrayOf(module1,module2,module3)
       val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3")

                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
       listviewprogramme.adapter = myListProgrammeAdapter
   }

                if(nombreSemestre.equals("4")){

                    val modules: Array<String> = arrayOf(module1,module2,module3,module4)
                    val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4")

                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
                    listviewprogramme.adapter = myListProgrammeAdapter
                }

                if(nombreSemestre.equals("5")){


                    val modules: Array<String> = arrayOf(module1,module2,module3,module4,module5)
                    val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4","semestre 5")

                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
                    listviewprogramme.adapter = myListProgrammeAdapter
                }

                if(nombreSemestre.equals("6")){

                    val modules: Array<String> = arrayOf(module1,module2,module3,module4,module5,module6)
                    val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4","semestre 5","semestre 6")

                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
                    listviewprogramme.adapter = myListProgrammeAdapter

                }

                if(nombreSemestre.equals("7")){

                    val modules: Array<String> = arrayOf(module1,module2,module3,module4,module5,module6,module7)
                    val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4","semestre 5","semestre 6","semestre 7")


                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
                    listviewprogramme.adapter = myListProgrammeAdapter
                }

                if(nombreSemestre.equals("8")){

                    val modules: Array<String> = arrayOf(module1,module2,module3,module4,module5,module6,module7,module8)
                    val semestres: Array<String> = arrayOf("semestre 1","semestre 2","semestre 3","semestre 4","semestre 5","semestre 6","semestre 7","semestre 8")

                    Log.d("size list", ListNomEquipePedagogique.size.toString())
                    val myListProgrammeAdapter = MyListProgrammeAdapter(this, modules, semestres)
                    listviewprogramme.adapter = myListProgrammeAdapter
                }


















/*
                body.add(semestre1)
                body.add(semestre2)
                body.add(semestre3)
                body.add(semestre4)
                body.add(semestre5)
                body.add(semestre6)
                body.add(semestre7)
                body.add(semestre8)


 var Max = maxId.get(maxId.size-1)
 var nbMax = Max.toInt()
                for(i in 0 until nbMax){

                    header.add("semestre"+(i+1))
                }

          /*      Toast.makeText(applicationContext,maxId.size.toString(), Toast.LENGTH_LONG).show()

                header.add("semestre1")
                header.add("semestre2")
                header.add("semestre3")
                header.add("semestre4")
                header.add("semestre5")
                header.add("semestre6")
                header.add("semestre7") */


  //val testExp : ExpandableListAdapter =ExpandableListAdapter(this,listexp,header,body)


listviewAdapter = ExpandableListAdapter(this,listexp,header,body)

   //listexp.setAdapter(ExpandableListAdapter(this,listexp,header,body))
listexp.setAdapter(listviewAdapter)
              //  Toast.makeText(applicationContext,"voilà"+, Toast.LENGTH_LONG).show()




              /*  listexp.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->



                }  */



                val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_list_item_1,ListDesModules)
                listModule.adapter = adapter
*/






            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
                    params.put("id_projet",id_projet)



                    return params
                }




            }



            queue2.add(req2)
            queue2.start()












            /** fin req 2 **/


            /** pour req3 **/












/*


    /** Req all Modules **/
    val listAllModule : ArrayList<Module> = ArrayList<Module>()

    val queue5 = Volley.newRequestQueue(this)


    // Request a string response from the provided URL.
    val req5 = object : StringRequest(Request.Method.POST, url4, Response.Listener <String> {

            response1  ->
        try {

            val obj = JSONObject(response1)
            val jsonArray: JSONArray = obj.getJSONArray("data")
            var intElement1 : String =""
            var intElement2 : String=""
            var intElement3 : String=""

            var descElement1 : String =""
            var descElement2 : String =""
            var descElement3 : String =""

            for (i in 0 until jsonArray.length()) {

                var jsonInner: JSONObject = jsonArray.getJSONObject(i)
           /*     var jsonInner2 : JSONObject = jsonArray.getJSONObject(3*i+1)
                var jsonInner3 : JSONObject =jsonArray.getJSONObject(3*i+2)
                */
                if(i%3==0){

                    intElement1 = jsonInner.get("intituleelement").toString()
                    descElement1 = jsonInner.get("description").toString()


                } else if (i%3==1){
                    intElement2 = jsonInner.get("intituleelement").toString()
                    descElement2 = jsonInner.get("description").toString()
                } else if(i%3==2){
                    intElement3 = jsonInner.get("intituleelement").toString()
                    descElement3 = jsonInner.get("description").toString()



                    listAllModule.add(
                        Module(
                            jsonInner.get("intitulemodule").toString(), jsonInner.get("objectif").toString()
                            , jsonInner.get("prerequis").toString(), jsonInner.get("notemodule").toString()
                            , jsonInner.get("evaluation").toString(), jsonInner.get("validation").toString()
                            ,intElement1, intElement2 , intElement3
                            , descElement1
                            ,descElement2
                            ,descElement3
                        )
                    )










                }




            }


            val adapter10 = ModuleAdapter(listAllModule)

            //now adding the adapter to recyclerview
            recylclerModule.adapter= adapter10




            /*     val adapter10 = ModuleAdapter(listAllModule)

                 //now adding the adapter to recyclerview
                 recylclerModule.adapter = adapter10 */


        } catch (e: JSONException){ e.printStackTrace()}







    },object : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {

            Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
            //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
        }
    })   {

        @Throws(AuthFailureError::class)
        override fun getParams(): Map<String, String> {

            val params = HashMap<String,String>()
            params.put("id_projet",id_projet)



            return params
        }




    }



    queue5.add(req5)
    queue5.start()


/** Fin **/






*/
















            /**  debuuuuuuuuuuut  **/


            var  encode : ArrayList<String> = ArrayList<String>()

            val queue6 = Volley.newRequestQueue(this)


            // Request a string response from the provided URL.
            val req6= object : StringRequest(Request.Method.POST,urlFile, Response.Listener <String> {

                    response1  ->
                try {

                    val obj = JSONObject(response1)
                    val jsonArray: JSONArray = obj.getJSONArray("data")



                    for (i in 0 until jsonArray.length()) {

                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                        listBitmapPage1.add(jsonInner.get("page1").toString())
                        listBitmapPage1.add(jsonInner.get("page2").toString())


                     //   bitmap3 = decodeImage(jsonInner.get("img").toString())

                    }


              //      val adapter10 = ModuleAdapter(listAllModule)



                } catch (e: JSONException){ e.printStackTrace()}







            },object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    Toast.makeText(applicationContext,"error", Toast.LENGTH_LONG).show()
                    //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                }
            })   {

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    val params = HashMap<String,String>()
params.put("id_projet",id_projet)



                    return params
                }




            }



            queue6.add(req6)
            queue6.start()
















            /** **/

            back.setOnClickListener{
                val intent = Intent(applicationContext, ProfileActivity::class.java)
                /* intent.putExtra("email",str_test)
                 intent.putExtra("nom",str_nom)
                 intent.putExtra("prenom",str_prenom)
                 intent.putExtra("profession",professionUser) */

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }








            var choix : String  =""

          param.setOnClickListener {

       /*    val  queue : RequestQueue = Volley.newRequestQueue(this);
       val imageLoader : ImageLoader  = ImageLoader(queue,LruBitmapCache());
        imageLoader.get("http://s017.radikal.ru/i413/1209/e7/648aa22cb947.jpg"));
*/


                /**  fiiiiiiiiiiin  **/


/*
                bitmap0 = loadBitmapFromView(page0, page0.width, page0.height)
                bitmap1 = loadBitmapFromView(page1, page1.width, page1.height)
                bitmap2 = loadBitmapFromView(page2, page2.width, page2.height)
                bitmap3 = loadBitmapFromView(page3, page3.width, page3.height)
                bitmap4 = loadBitmapFromView(pageequipe, pageequipe.width,pageequipe.height)
                bitmap5 = loadBitmapFromView(page4,page4.width, page4.height)
                bitmap6 = loadBitmapFromView(page5,page5.width, page5.height)
                bitmap7 = loadBitmapFromView(page6,page6.width, page6.height)
                bitmap8 = loadBitmapFromView(page7,page7.width, page7.height)
*/



              // bitmap3 = loadBitmapFromView(page3, page3.width, page3.height);


               // bitmap6 = loadBitmapFromView(recylclerModule.getChildAt(0), recylclerModule.getChildAt(0).width,recylclerModule.getChildAt(0).height );
              //  bitmap2 = loadBitmapFromView(notrepagee, notrepagee.width, notrepagee.height);
      //       bitmap6= getRecyclerViewScreenshot(recylclerModule)


                val myBuilder = AlertDialog.Builder(this)
                //DATA SOURCE
                //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
                //SET PROPERTIES USING METHOD CHAINING
                val arr = arrayOf("Enregistrer pdf","Annuler")
                val arrChoix = arrayOf("1","2")

                myBuilder.setTitle("Action ").setItems(arr) { dialogInterface, position ->

                choix = arrChoix[position]

                    if(choix.equals("1")){

                        listBitmapbm.add(loadBitmapFromView(page0, page0.width, page0.height))
                        listBitmapbm.add(loadBitmapFromView(page1, page1.width, page1.height))
                        listBitmapbm.add(loadBitmapFromView(page2, page2.width, page2.height))
                        listBitmapbm.add(loadBitmapFromView(page3, page3.width, page3.height))
                        listBitmapbm.add(loadBitmapFromView(pageequipe, pageequipe.width,pageequipe.height))
                        listBitmapbm.add(loadBitmapFromView(page4,page4.width, page4.height))
                        listBitmapbm.add(loadBitmapFromView(page5,page5.width, page5.height))
                        listBitmapbm.add(loadBitmapFromView(page6,page6.width, page6.height))
                        listBitmapbm.add(loadBitmapFromView(page7,page7.width, page7.height))

                        for(i in 0 until listBitmapPage1.size){

                            listBitmapbm.add(decodeImage(listBitmapPage1.get(i)))

                        }

                        createPdf()
                    }

                    if(choix.equals("2")){

                        val intent = Intent(applicationContext, ProfileActivity::class.java)
                        /* intent.putExtra("email",str_test)
                         intent.putExtra("nom",str_nom)
                         intent.putExtra("prenom",str_prenom)
                         intent.putExtra("profession",professionUser) */

                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)
                    }

                //CREATE DIALOG

                }

                myDialog = myBuilder.create()
                //SHOW DIALOG

                myDialog.show()














            }



























        }


    private  fun MesElement (id_module : String) : ArrayList<Element>{

        val ListElement : ArrayList<Element> = ArrayList<Element>()

        val queue30 = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req30 = object : StringRequest(Request.Method.POST, url3, Response.Listener <String> {

                response1  ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")




                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)


 ListElement.add(Element(jsonInner.get("intitule").toString(),jsonInner.get("description").toString(),jsonInner.get("hrtd").toString()))



                    /*   ListDescElement.add(jsonInner.get("description").toString()+ "\n"+"Nombre des Heures :" +"\n"+"TD :"+jsonInner.get("hrtd").toString()
                                 +"\n"+"Cours :"+jsonInner.get("hrcours").toString()
                                 +"\n"+"TP :"+jsonInner.get("hrtp").toString()
                         )
                         ListIntituleElement.add(jsonInner.get("intitule").toString()) */
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
                params.put("id",id_module)



                return params
            }




        }



        queue30.add(req30)
        queue30.start()


return ListElement
    }

    /** **/
    fun loadBitmapFromView(v: View, width: Int, height: Int): Bitmap {
        val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        v.draw(c)

        return b
    }
    /** **/



    /*public fun onRequestPermissionsResult(requestCode: int,
                                        permissions[] : String, grantResults : int[] ) {
    switch (requestCode) {
        case 1: {
            if (!(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(addAlarm.this, "Permission denied to access your location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
    */



    @SuppressLint("NewApi")
    private fun createPdf() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //  Display display = wm.getDefaultDisplay();
        val displaymetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val hight = displaymetrics.heightPixels.toFloat()
        val width = displaymetrics.widthPixels.toFloat()

        val convertHighet = hight.toInt()
        val convertWidth = width.toInt()

        //        Resources mResources = getResources();
//     Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        val document = PdfDocument()




for(i in 0 until listBitmapbm.size ) {
    /** Debut **/
    //9+i
    val pageInfo4 = PdfDocument.PageInfo.Builder(convertWidth, convertHighet, i).create()
    val page4 = document.startPage(pageInfo4)
    val canvas4 = page4.canvas
    val paint4 = Paint()
    canvas4.drawPaint(paint4)
    bitmap9 = Bitmap.createScaledBitmap(listBitmapbm.get(i), convertWidth, convertHighet, true)
    paint4.setColor(Color.BLUE)
    canvas4.drawBitmap(bitmap9, 0F, 0F, null)
    document.finishPage(page4)



    /** fin **/

}



        /*
        /** Debut **/
        val pageInfo6 = PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 5).create()
        val page6 = document.startPage(pageInfo6)
        val canvas6= page6.canvas
        val paint6 = Paint()
        canvas6.drawPaint(paint6)
        bitmap6= Bitmap.createScaledBitmap(bitmap6, convertWidth+100, convertHighet+100, true)
        paint4.setColor(Color.BLUE)
        canvas4.drawBitmap(bitmap6,0F,0F,null)
        document.finishPage(page6)

        /** fin **/  */





        // write the document content
        val targetPdf = "/sdcard/"+projetintitule+".pdf"
        val filePath: File
        filePath = File(targetPdf)
            //Environment.getExternalStorageState()+





        try {
            document.writeTo(FileOutputStream(filePath))

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show()
        }



        // close the document
        document.close()
        Toast.makeText(this, "PDF enregistré!!!", Toast.LENGTH_SHORT).show()

     //   openGeneratedPDF()

    }

   private fun openGeneratedPDF(){
        val file : File = File("/sdcard/projetPedagogique.pdf");
        if (file.exists())
        {
            val  intent : Intent =Intent(Intent.ACTION_VIEW);
             val uri : Uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(e: ActivityNotFoundException)
            {
                Toast.makeText(this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
    /** **/



    /** **/

    fun getRecyclerViewScreenshot(view: RecyclerView): Bitmap {
        val size = view.adapter!!.itemCount
        val holder = view.adapter!!.createViewHolder(view, 0)
        view.adapter!!.onBindViewHolder(holder, 0)
        holder.itemView.measure(
            View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        holder.itemView.layout(0, 0, holder.itemView.measuredWidth, holder.itemView.measuredHeight)
        val bigBitmap = Bitmap.createBitmap(
            view.measuredWidth, holder.itemView.measuredHeight * size,
            Bitmap.Config.ARGB_8888
        )
        val bigCanvas = Canvas(bigBitmap)
        bigCanvas.drawColor(Color.WHITE)
        val paint = Paint()
        var iHeight = 0
        holder.itemView.isDrawingCacheEnabled = true

        holder.itemView.buildDrawingCache()
        bigCanvas.drawBitmap(holder.itemView.drawingCache, 0f, iHeight.toFloat(), paint)
        holder.itemView.isDrawingCacheEnabled = false
        holder.itemView.destroyDrawingCache()
        iHeight += holder.itemView.measuredHeight
        for (i in 1 until size) {
            view.adapter!!.onBindViewHolder(holder, i)
            holder.itemView.isDrawingCacheEnabled = true
            holder.itemView.buildDrawingCache()
            bigCanvas.drawBitmap(holder.itemView.drawingCache, 0f, iHeight.toFloat(), paint)
            iHeight += holder.itemView.measuredHeight
            holder.itemView.isDrawingCacheEnabled = false
            holder.itemView.destroyDrawingCache()
        }
        return bigBitmap
    }


    /** **/



    private fun decodeImage(base64String : String) : Bitmap {
        val imageBytes : ByteArray = Base64.decode(base64String, Base64.DEFAULT)
        val decodedImage : Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }
















}
