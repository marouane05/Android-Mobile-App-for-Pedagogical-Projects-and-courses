package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.graphics.Bitmap

import android.graphics.Canvas
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.ByteArrayOutputStream
import java.util.*
import  android.util.*
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fin_projet.*
import kotlinx.android.synthetic.main.activity_ppedagogique.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.Base64
import java.io.*
import kotlin.collections.ArrayList

class FinProjet : AppCompatActivity() {


    private val conf : config= config()
    private var Base :String= conf.Base
    private val url: String = Base+"/projet-pedagogique/api/controller/AddImage.php"



    lateinit var session : SessionManager
    lateinit var nom : TextView
    lateinit var description : TextView
    lateinit var  debouches : TextView
    lateinit var  cout : TextView
    lateinit var coordinateur : TextView
    lateinit var admission : TextView
    lateinit var  module1 : TextView
    lateinit var  module2 : TextView
    lateinit var  module3 : TextView
    lateinit var  module4 : TextView
    lateinit var  module5 : TextView
    lateinit var  module6 : TextView
    lateinit var  module7 : TextView
    lateinit var  module8 : TextView
    lateinit var  module9 : TextView
    lateinit var  module10 : TextView
    lateinit var  module11 : TextView
    lateinit var  module12 : TextView
    lateinit var terminer : Button
    lateinit var  fin : Button
// lateinit var  imageTest : ImageView
    lateinit var  monLayout : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin_projet)




        var id_projet:String = intent.getStringExtra("id")

         monLayout = this.findViewById(R.id.monLayout)
        terminer = this.findViewById(R.id.terminer)
        coordinateur = this.findViewById(R.id.coordinateur)
      //  imageTest = this.findViewById(R.id.imagetest)

        var Semestre5_module1 = intent.getStringExtra("module1")
        var Semestre5_module2 = intent.getStringExtra("module2")
        var Semestre5_module3 = intent.getStringExtra("module3")
        var Semestre5_module4 = intent.getStringExtra("module4")
        var Semestre5_module5 = intent.getStringExtra("module5")
        var Semestre5_module6 = intent.getStringExtra("module6")

        var Semestre5_heure1 = intent.getStringExtra("heure1")
        var Semestre5_heure2 = intent.getStringExtra("heure2")
        var Semestre5_heure3 = intent.getStringExtra("heure3")
        var Semestre5_heure4 = intent.getStringExtra("heure4")
        var Semestre5_heure5 = intent.getStringExtra("heure5")
        var Semestre5_heure6 = intent.getStringExtra("heure6")


        var Semestre6_module1 = intent.getStringExtra("module7")
        var Semestre6_module2 = intent.getStringExtra("module8")
        var Semestre6_module3 = intent.getStringExtra("module9")
        var Semestre6_module4 = intent.getStringExtra("module10")
        var Semestre6_module5 = intent.getStringExtra("module11")
        var Semestre6_module6 = intent.getStringExtra("module12")

        var Semestre6_heure1 = intent.getStringExtra("heure7")
        var Semestre6_heure2 = intent.getStringExtra("heure8")
        var Semestre6_heure3 = intent.getStringExtra("heure9")
        var Semestre6_heure4 = intent.getStringExtra("heure10")
        var Semestre6_heure5 = intent.getStringExtra("heure11")
        var Semestre6_heure6 = intent.getStringExtra("heure12")




        var nom_projet= intent.getStringExtra("nom")
        var admission_projet=intent.getStringExtra("admission")
        var debouche_projet=intent.getStringExtra("debouche")
        var cout_projet =intent.getStringExtra("cout")
        var description_projet=intent.getStringExtra("desc")

        session = SessionManager(applicationContext)
        session.checkLogin()
        var user = session.getUserDetails()
        val emailProf = user.get(SessionManager.KEY_EMAIL)






        nom  = this.findViewById(R.id.nom)
        admission = this.findViewById(R.id.admission)
        description= this.findViewById(R.id.objectif)
        debouches = this.findViewById(R.id.debouches)
       // fin = this.findViewById(R.id.fin)

        module1 = this.findViewById(R.id.module1)
        module2 = this.findViewById(R.id.module2)
        module3 = this.findViewById(R.id.module3)
        module4 = this.findViewById(R.id.module4)
        module5 = this.findViewById(R.id.module5)
        module6 = this.findViewById(R.id.module6)
        module7 = this.findViewById(R.id.module7)
        module8 = this.findViewById(R.id.module8)
        module9 = this.findViewById(R.id.module9)
        module10 = this.findViewById(R.id.module10)
        module11 = this.findViewById(R.id.module11)
        module12 = this.findViewById(R.id.module12)



        nom.setText("Licence professionnel"+" "+nom_projet)
        admission.setText(admission_projet)
        description.setText(" "+description_projet)
        debouches.setText(debouche_projet)
        coordinateur.setText("Email :"+""+emailProf)

        module1.setText(Semestre5_module1+"("+Semestre5_heure1+" H)")
        module2.setText(Semestre5_module2+"("+Semestre5_heure2+" H)")
        module3.setText(Semestre5_module3+"("+Semestre5_heure3+" H)")
        module4.setText(Semestre5_module4+"("+Semestre5_heure4+" H)")
        module5.setText(Semestre5_module5+"("+Semestre5_heure5+" H)")
        module6.setText(Semestre5_module6+"("+Semestre5_heure6+" H)")
        module7.setText(Semestre6_module1+"("+Semestre6_heure1+" H)")
        module8.setText(Semestre6_module2+"("+Semestre6_heure2+" H)")
        module9.setText(Semestre6_module3+"("+Semestre6_heure3+" H)")
        module10.setText(Semestre6_module4+"("+Semestre6_heure4+" H)")
        module11.setText(Semestre6_module5+"("+Semestre6_heure5+" H)")
        module12.setText(Semestre6_module6+"("+Semestre6_heure6+" H)")




            var result : String =""
       /*     var bitmap : Bitmap
            bitmap = Bitmap.createBitmap(monLayout.width,monLayout.height,Bitmap.Config.ARGB_8888)
            var  canvas :  Canvas = Canvas(bitmap)
            monLayout.draw(canvas)  */

            //   imageTest.setImageBitmap(bitmap)



            terminer.setOnClickListener {

                Toast.makeText(applicationContext,description_projet, Toast.LENGTH_LONG).show()

          /** Debut request **/


      /*    val queue = Volley.newRequestQueue(this)


                // Request a string response from the provided URL.
                val req = object : StringRequest(Request.Method.POST, url, Response.Listener <String> {

                        response1  ->
                    try {

                        val obj = JSONObject(response1)
                        val jsonArray: JSONArray = obj.getJSONArray("reponse")
                        var str_user: String = ""



                        for (i in 0 until jsonArray.length()) {
                            var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                            str_user =jsonInner.get("loggedin").toString()

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
                        params.put("image",getStringImage(bitmap))
                        params.put("id_projet",id_projet)




                        return params
                    }




                }



                queue.add(req)
                queue.start()

*/
          val intent = Intent(applicationContext, ProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


                startActivity(intent)


                /** Fin Request **/

      }






    }

/*
    private fun saveToInternalStorage(bitmapImage: Bitmap): String {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, "profile.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return directory.absolutePath
    }
*/

    fun getStringImage(bmp: Bitmap): String {
        val baos  = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        val result : String = android.util.Base64.encodeToString(imageBytes,android.util.Base64.DEFAULT)
        return  result

    }






}
