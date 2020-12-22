package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class AffecteModule : AppCompatActivity() {

    lateinit var  prof1 : ImageView
    lateinit var  prof2 : ImageView
    lateinit var  prof3 : ImageView
    lateinit var  prof4 : ImageView
    lateinit var  prof5 : ImageView
    lateinit var  prof6 : ImageView
    lateinit var  prof7 : ImageView
    lateinit var  prof8 : ImageView

    lateinit var mod1 : EditText
    lateinit var mod2 : EditText
    lateinit var mod3 : EditText
    lateinit var mod4 : EditText
    lateinit var mod5 : EditText
    lateinit var mod6 : EditText
    lateinit var mod7 : EditText
    lateinit var mod8 : EditText
    lateinit var titre_semestre : TextView

    lateinit var add11 : ImageView
    lateinit var add22 : ImageView
    lateinit var add33 : ImageView
    lateinit var  add1 : ImageView
    lateinit var  add2 : ImageView
    lateinit var  add3 : ImageView
    lateinit var  add4 : ImageView

    lateinit var row22 : TableRow
    lateinit var row33 : TableRow
    lateinit var row44 : TableRow
    lateinit var row1 : TableRow
    lateinit var  row2 : TableRow
    lateinit var  row3 : TableRow
    lateinit var row4 : TableRow
lateinit var back : LinearLayout

    lateinit var  continuer : Button
    lateinit var  terminer : Button

    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/ListProf.php"
    private val url2: String = Base+"/projet-pedagogique/api/controller/affectModule.php"

    private var nombre = 4

    private lateinit var myDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affecte_module)

titre_semestre = this.findViewById(R.id.textView3)
        prof1 = this.findViewById(R.id.prof1)
        prof2 = this.findViewById(R.id.prof2)
        prof3 = this.findViewById(R.id.prof3)
        prof4 = this.findViewById(R.id.prof4)
        prof5 = this.findViewById(R.id.prof5)
        prof6 = this.findViewById(R.id.prof6)
        prof7 = this.findViewById(R.id.prof7)
        prof8 = this.findViewById(R.id.prof8)

        mod1 = this.findViewById(R.id.mod1)
        mod2 = this.findViewById(R.id.mod2)
        mod3 = this.findViewById(R.id.mod3)
        mod4 = this.findViewById(R.id.mod4)
        mod5 = this.findViewById(R.id.mod5)
        mod6 = this.findViewById(R.id.mod6)
        mod7 = this.findViewById(R.id.mod7)
        mod8 = this.findViewById(R.id.mod8)

        add11 = this.findViewById(R.id.add11)
        add22= this.findViewById(R.id.add22)
        add33= this.findViewById(R.id.add33)
        add1 = this.findViewById(R.id.add1)
        add2=this.findViewById(R.id.add2)
        add3=this.findViewById(R.id.add3)
        add4=this.findViewById(R.id.add4)

        row22=this.findViewById(R.id.row22)
        row33=this.findViewById(R.id.row33)
        row44=this.findViewById(R.id.row44)
        row1= this.findViewById(R.id.row1)
        row2= this.findViewById(R.id.row2)
        row3= this.findViewById(R.id.row3)
        row4= this.findViewById(R.id.row4)

        terminer= this.findViewById(R.id.terminer)
        continuer = this.findViewById(R.id.continuer)

        back = this.findViewById(R.id.back)
var listProf = ArrayList<String>()
var listProfbyId = ArrayList<String>()


        var chooseProfId1 = ""
        var chooseProfId2= ""
        var chooseProfId3 = ""
        var chooseProfId4 = ""
        var chooseProfId5 = ""
        var chooseProfId6 = ""
        var chooseProfId7 = ""
        var chooseProfId8 = ""






        var id_projet:String = intent.getStringExtra("id")

        var id_semestre = intent.getStringExtra("nbr_semestre")

        var nbr_semestre = id_semestre.toInt()

        var departSemestre = intent.getStringExtra("departSemestre")
        var nbr_departSemestre = departSemestre.toInt()



titre_semestre.text= "Semestre " + intent.getStringExtra("departSemestre")

add22.visibility=View.INVISIBLE
add33.visibility=View.INVISIBLE
add1.visibility=View.INVISIBLE
add2.visibility =View.INVISIBLE
add3.visibility=View.INVISIBLE
add4.visibility=View.INVISIBLE


        row22.visibility = View.INVISIBLE
        row33.visibility = View.INVISIBLE
        row44.visibility= View.INVISIBLE
        row1.visibility= View.INVISIBLE
        row2.visibility= View.INVISIBLE
        row3.visibility= View.INVISIBLE
        row4.visibility= View.INVISIBLE

        terminer.visibility = View.INVISIBLE


        add11.setOnClickListener {

            nombre++
            add11.visibility= View.INVISIBLE
            add22.visibility =View.VISIBLE
            row22.visibility= View.VISIBLE


        }




        add22.setOnClickListener {

            nombre++
            add22.visibility= View.INVISIBLE
            add33.visibility =View.VISIBLE
            row33.visibility= View.VISIBLE


        }


        add33.setOnClickListener {

            nombre++
            add33.visibility= View.INVISIBLE
            add1.visibility =View.VISIBLE
            row44.visibility= View.VISIBLE


        }






        add1.setOnClickListener {

            nombre++
            add1.visibility= View.INVISIBLE
            add2.visibility =View.VISIBLE
            row1.visibility= View.VISIBLE


        }

        add2.setOnClickListener {
            nombre++
            add2.visibility= View.INVISIBLE
            add3.visibility =View.VISIBLE
            row2.visibility= View.VISIBLE


        }

        add3.setOnClickListener {
            nombre++
            add3.visibility= View.INVISIBLE
            add4.visibility =View.VISIBLE
            row3.visibility= View.VISIBLE


        }

        add4.setOnClickListener {
            nombre++
            add4.visibility= View.INVISIBLE

            row4.visibility= View.VISIBLE


        }





back.setOnClickListener {


    val intent = Intent(applicationContext, ProfileActivity::class.java)
    /* intent.putExtra("email",str_test)
     intent.putExtra("nom",str_nom)
     intent.putExtra("prenom",str_prenom)
     intent.putExtra("profession",professionUser) */

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    startActivity(intent)
}






/** ajout des profs à module **/

     var    ListProfByModule = ArrayList<String>()
     var listModule  = ArrayList<String>()


        var id_prof1 =""



        /** Debut de requete 1 **/
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

                    // cycle = jsonInner.get("nom_cycle").toString()
                    //date = jsonInner.get("date_demande").toString()

                    listProf.add(" "+jsonInner.get("nom").toString()+" "+jsonInner.get("prenom").toString())
                    listProfbyId.add(jsonInner.get("id").toString())


                }





            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){

    Toast.makeText(this, listProf.get(i), Toast.LENGTH_LONG).show()
} */




        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {


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



        /** fin requete 1 **/



















        prof1.setOnClickListener {





            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
         //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()

            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId1=arrId[position].toString()
              Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG


            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }





        prof2.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId2=arrId[position].toString()

              Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }

        prof3.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId3=arrId[position].toString()
                Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }


        prof4.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                  chooseProfId4=arrId[position].toString()

                Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }



        prof5.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId5=arrId[position].toString()

                Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }



        prof6.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId6=arrId[position].toString()

                Toast.makeText(this, arr[position].toString()+arrId[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }


        prof7.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId7=arrId[position].toString()

                Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOG
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }



        prof8.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = listProf.toTypedArray()
            val arrId = listProfbyId.toTypedArray()
            myBuilder.setTitle("Choisir professeur").setItems(arr) { dialogInterface, position ->

                chooseProfId8=arrId[position].toString()

                Toast.makeText(this, arr[position].toString(), Toast.LENGTH_SHORT).show() }
            //CREATE DIALOGs
            myDialog = myBuilder.create()
            //SHOW DIALOG
            myDialog.show()






        }

        if(nbr_semestre==nbr_departSemestre){

            continuer.visibility = View.INVISIBLE
            terminer.visibility=View.VISIBLE
        }



        continuer.setOnClickListener {

            listModule.add(0,mod1.text.toString())
            listModule.add(1,mod2.text.toString())
            listModule.add(2,mod3.text.toString())
            listModule.add(3,mod4.text.toString())
            listModule.add(4,mod5.text.toString())
            listModule.add(5,mod6.text.toString())
            listModule.add(6,mod7.text.toString())
            listModule.add(7,mod8.text.toString())

            ListProfByModule.add(0,chooseProfId1)
            ListProfByModule.add(1,chooseProfId2)
            ListProfByModule.add(2,chooseProfId3)
            ListProfByModule.add(3,chooseProfId4)
            ListProfByModule.add(4,chooseProfId5)
            ListProfByModule.add(5,chooseProfId6)
            ListProfByModule.add(6,chooseProfId7)
            ListProfByModule.add(7,chooseProfId8)








/** Debut requet **/
for(j in 0 until ListProfByModule.size){



    val queue = Volley.newRequestQueue(this)


           // Request a string response from the provided URL.
           val req = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

                   response1  ->
               try {

                   val obj = JSONObject(response1)
                   val jsonArray: JSONArray = obj.getJSONArray("data")
                   var str_user: String = ""
                   var cycle : String=""
                   var date : String=""

                   for (i in 0 until jsonArray.length()) {
                       var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                       // cycle = jsonInner.get("nom_cycle").toString()
                       //date = jsonInner.get("date_demande").toString()




                   }





               } catch (e: JSONException){ e.printStackTrace()}
               // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){


} */




           },object : Response.ErrorListener {
               override fun onErrorResponse(error: VolleyError?) {


                   //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
               }
           })   {

               @Throws(AuthFailureError::class)
               override fun getParams(): Map<String, String> {

                   val params = HashMap<String,String>()

                   params.put("nom_module",listModule.get(j))
                   params.put("id_prof",ListProfByModule.get(j))
                   params.put("id_semestre",nbr_departSemestre.toString())
                   params.put("id_projet",id_projet)


                   return params
               }




           }



           queue.add(req)
           queue.start()

}

                /** fin requete 1 **/

          if( nbr_departSemestre < nbr_semestre ) {
               var newVal = nbr_departSemestre+1
               val intent = Intent(applicationContext, AffecteModule::class.java)
               intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
               intent.putExtra("departSemestre", newVal.toString())
               intent.putExtra("id", id_projet)
              intent.putExtra("nbr_semestre",id_semestre)

               startActivity(intent)


           }





































        }

terminer.setOnClickListener {

    listModule.add(0,mod1.text.toString())
    listModule.add(1,mod2.text.toString())
    listModule.add(2,mod3.text.toString())
    listModule.add(3,mod4.text.toString())
    listModule.add(4,mod5.text.toString())
    listModule.add(5,mod6.text.toString())
    listModule.add(6,mod7.text.toString())
    listModule.add(7,mod8.text.toString())

    ListProfByModule.add(0,chooseProfId1)
    ListProfByModule.add(1,chooseProfId2)
    ListProfByModule.add(2,chooseProfId3)
    ListProfByModule.add(3,chooseProfId4)
    ListProfByModule.add(4,chooseProfId5)
    ListProfByModule.add(5,chooseProfId6)
    ListProfByModule.add(6,chooseProfId7)
    ListProfByModule.add(7,chooseProfId8)




    /** Debut requet **/
    for(j in 0 until ListProfByModule.size){

/*
        Toast.makeText(this, nbr_semestre.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, id_projet, Toast.LENGTH_SHORT).show()
        Toast.makeText(this, ListProfByModule.get(j) +" enseigne "+listModule.get(j) , Toast.LENGTH_LONG).show()

*/

        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req = object : StringRequest(Request.Method.POST, url2, Response.Listener <String> {

                response1  ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")
                var str_user: String = ""
                var cycle : String=""
                var date : String=""

                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    // cycle = jsonInner.get("nom_cycle").toString()
                    //date = jsonInner.get("date_demande").toString()




                }





            } catch (e: JSONException){ e.printStackTrace()}
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin
/* for(i in 0 until listProf.size){


} */


 /** Passer après recevoir réponse **/
            val intent = Intent(applicationContext, FinAffectation::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


            startActivity(intent)
/** **/

        },object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {


                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        })   {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String,String>()

                params.put("nom_module",listModule.get(j))
                params.put("id_prof",ListProfByModule.get(j))
                params.put("id_semestre",nbr_departSemestre.toString())
                params.put("id_projet",id_projet)


                return params
            }




        }



        queue.add(req)
        queue.start()

    }

    /** fin requete 1 **/





}






    }





fun openDialog(view : View){



    





}








}




