package com.example.gestiondesprojetspdagogiques

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class DescriptifModule : AppCompatActivity() {



    val conf : config=config()
    var Base :String= conf.Base

    val url1 = Base+"/projet-pedagogique/api/controller/addModuleFilJs.php"
    val url3 = Base+"/projet-pedagogique/api/controller/uploadBydecode.php"
    val url4 = Base+"/projet-pedagogique/api/controller/Updateconfirmation.php"
    //    val url1 = Base+"/projet-pedagogique/api/controller/addModuleFile.php"

    lateinit var intitule : TextView
    lateinit var prof : TextView
    lateinit var semestre : TextView
    lateinit var objectifs : TextView
    lateinit var evaluation : TextView
    lateinit var validation : TextView
    lateinit var  note : TextView
    lateinit var tp1 : TextView
    lateinit var tp2 : TextView
    lateinit var tp3 : TextView
    lateinit var cours1 : TextView
    lateinit var cours2 : TextView
    lateinit var cours3 : TextView
    lateinit var td1 : TextView
    lateinit var td2 : TextView
    lateinit var td3 : TextView
    lateinit var elem1desc : TextView
    lateinit var elem2desc : TextView
    lateinit var elem3desc : TextView
    lateinit var elem1 : TextView
    lateinit var elem2 : TextView
    lateinit var elem3 : TextView
    lateinit var prerequis : TextView
    lateinit var back : RelativeLayout
    lateinit var param : RelativeLayout
    lateinit var page2 : RelativeLayout
    lateinit var  page1 : RelativeLayout
    private lateinit var myDialog: AlertDialog
    lateinit var button : Button


    private var listBitmapbm : ArrayList<Bitmap> = ArrayList<Bitmap>()
    private lateinit var bitmap0 : Bitmap
    private  lateinit var bitmap1 : Bitmap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descriptif_module)


        intitule = this.findViewById(R.id.intitule)
        prof = this.findViewById(R.id.prof)
        semestre = this.findViewById(R.id.semestre)
        objectifs = this.findViewById(R.id.objectifs)
        evaluation = this.findViewById(R.id.evaluation)
        validation = this.findViewById(R.id.validation)
        note = this.findViewById(R.id.note)


        tp1 = this.findViewById(R.id.tp1)
        tp2 = this.findViewById(R.id.tp2)
        tp3 = this.findViewById(R.id.tp3)

        td1 = this.findViewById(R.id.td1)
        td2 = this.findViewById(R.id.td2)
        td3 = this.findViewById(R.id.td3)

        cours1 = this.findViewById(R.id.cours1)
        cours2 = this.findViewById(R.id.cours2)
        cours3 = this.findViewById(R.id.cours3)

        elem1desc = this.findViewById(R.id.elem1desc)
        elem2desc = this.findViewById(R.id.elem2desc)
        elem3desc = this.findViewById(R.id.elem3desc)

        elem1 = this.findViewById(R.id.elem1)
        elem2 = this.findViewById(R.id.elem2)
        elem3 = this.findViewById(R.id.elem3)

        prerequis = this.findViewById(R.id.prerequis)

        back = this.findViewById(R.id.back)
        param = this.findViewById(R.id.param)

        page1 = this.findViewById(R.id.page1)
        page2 = this.findViewById(R.id.page2)

     back = this.findViewById(R.id.back)


        var id_module =  intent.getStringExtra("id_module")
     var objectif =    intent.getStringExtra("objectif")
     var modeevaluation =   intent.getStringExtra("modeevaluation")
      var notemodule =   intent.getStringExtra("notemodule")
       var mvalidation =  intent.getStringExtra("validation")
     var id_projet =   intent.getStringExtra("id_projet")
     var mprerequis =    intent.getStringExtra("prerequis")
      var melem1 =   intent.getStringExtra("elem1")
     var melem2 =    intent.getStringExtra("elem2")
       var melem3 = intent.getStringExtra("elem3")
      var hrtd1 =  intent.getStringExtra("hrtd1")
      var hrtd2 =   intent.getStringExtra("hrtd2")
       var hrtd3 =  intent.getStringExtra("hrtd3")
     var hrcours1 =    intent.getStringExtra("hrcours1")
    var hrcours2 =     intent.getStringExtra("hrcours2")
     var hrcours3 =    intent.getStringExtra("hrcours3")
     var hrtp1 =    intent.getStringExtra("hrtp1")
      var hrtp2 =   intent.getStringExtra("hrtp2")
      var hrtp3 =   intent.getStringExtra("hrtp3")
       var elemdesc1 =  intent.getStringExtra("elemdesc1")
     var elemdesc2 =   intent.getStringExtra("elemdesc2")
     var elemdesc3 =   intent.getStringExtra("elemdesc3")
    var mprof =    intent.getStringExtra("prof")
      var msemestre =  intent.getStringExtra("semestre")
        var mintitule = intent.getStringExtra("mintitule")


        intitule.setText(mintitule)
        prof.setText("Mr. "+mprof)
              semestre.setText(msemestre)
        objectifs.setText(objectif)
        evaluation.setText(modeevaluation)
        validation.setText(mvalidation)
        note.setText(notemodule)

        tp1.setText(hrtp1)
        tp2.setText(hrtp2)
        tp3.setText(hrtp3)

        td1.setText(hrtd1)
        td2.setText(hrtd2)
        td3.setText(hrtd3)

        cours1.setText(hrcours1)
        cours2.setText(hrcours2)
        cours3.setText(hrcours3)

        elem1desc.setText(elemdesc1)
        elem2desc.setText(elemdesc2)
        elem3desc.setText(elemdesc3)

        elem1.setText(melem1)
        elem2.setText(melem2)
        elem3.setText(melem3)

        prerequis.setText(mprerequis)



        back.setOnClickListener {

            val intent = Intent(applicationContext, ProfileActivity::class.java)
            /* intent.putExtra("email",str_test)
             intent.putExtra("nom",str_nom)
             intent.putExtra("prenom",str_prenom)
             intent.putExtra("profession",professionUser) */

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)


        }


var encodeList : ArrayList<String> = ArrayList<String>()







      param.setOnClickListener {


          val myBuilder = AlertDialog.Builder(this)
          //DATA SOURCE
          //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
          //SET PROPERTIES USING METHOD CHAINING
          val arr = arrayOf("Envoyer ","Enregistrer","Annuler")
          val arrChoix = arrayOf("1","2","3")
          var choix = ""
          myBuilder.setTitle("Action ").setItems(arr) { dialogInterface, position ->

              choix = arrChoix[position]

              if(choix.equals("1")) {


                  encodeList.add(encodeImage(loadBitmapFromView(page1, page1.width, page1.height)).trim())
                  encodeList.add(encodeImage(loadBitmapFromView(page2, page2.width, page2.height)).trim())

                  /** test req2 **/
                  val queue = Volley.newRequestQueue(this)

                  for (i in 0 until 2) {
                      // Request a string response from the provided URL.
                      val req = object : StringRequest(Request.Method.POST, url3, Response.Listener<String> {

                              response1 ->
                          try {

                              val obj = JSONObject(response1)
                              val jsonArray: JSONArray = obj.getJSONArray("response")




                              for (i in 0 until jsonArray.length()) {
                                  var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                                  Log.d("res", jsonInner.get("loggedin").toString())
                                  //  departement = jsonInner.getString("departement").toString()

                              }

                          } catch (e: JSONException) {
                              e.printStackTrace()
                          }
                          // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin


                      }, object : Response.ErrorListener {
                          override fun onErrorResponse(error: VolleyError?) {

                              Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
                              //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                          }
                      }) {


                          override fun getParams(): Map<String, String> {
                              val params: HashMap<String, String> = HashMap<String, String>()


                              params.put("id_module", id_module)
                              params.put("id_page", "page" + i)
                              params.put("fichier", encodeList.get(i).trim())

                              return params
                          }


                      }



                      queue.add(req)
                      queue.start()

                  }
                      /** **/

                      /** test req2 **/
                      val queue2 = Volley.newRequestQueue(this)


                          // Request a string response from the provided URL.
                          val req2 = object : StringRequest(Request.Method.POST, url4, Response.Listener<String> {

                                  response1 ->
                              try {

                                  val obj = JSONObject(response1)
                                  val jsonArray: JSONArray = obj.getJSONArray("response")




                                  for (i in 0 until jsonArray.length()) {
                                      var jsonInner: JSONObject = jsonArray.getJSONObject(i)



                                  }

                              } catch (e: JSONException) {
                                  e.printStackTrace()
                              }
                              // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin


                          }, object : Response.ErrorListener {
                              override fun onErrorResponse(error: VolleyError?) {

                                  Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
                                  //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                              }
                          }) {


                              override fun getParams(): Map<String, String> {
                                  val params: HashMap<String, String> = HashMap<String, String>()


                                  params.put("id_module",id_module)


                                  return params
                              }


                          }



                          queue2.add(req2)
                          queue2.start()


                          /** **/


                  /** Req 1 **/


                  val queue4 = Volley.newRequestQueue(this)


                  // Request a string response from the provided URL.
                  val req4 = object : StringRequest(Request.Method.POST, url1, Response.Listener <String> {

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
                          params.put("id_projet",id_projet)
                          params.put("page_un",id_module+"/page0.bmp")
                          params.put("page_deux",id_module+"/page0.bmp")



                          return params
                      }




                  }



                  queue4.add(req4)
                  queue4.start()


















                  /** Fin Req 1 **/


              }


              if(choix.equals("2")){



                listBitmapbm.add(loadBitmapFromView(page1, page1.width, page1.height))
                 listBitmapbm.add(loadBitmapFromView(page2, page2.width, page2.height))
// bitmap1 = decodeImage(encode2)
                  //   Log.d("image1",encode2)

                  // bitmap6 = loadBitmapFromView(recylclerModule.getChildAt(0), recylclerModule.getChildAt(0).width,recylclerModule.getChildAt(0).height );
                  //  bitmap2 = loadBitmapFromView(notrepagee, notrepagee.width, notrepagee.height);




                  createPdf();

              }



              if(choix.equals("3")){

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







    fun loadBitmapFromView(v: View, width: Int, height: Int): Bitmap {
        val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        v.draw(c)

        return b
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun createPdf() {

        val moduleint = intitule.text.toString()
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
            bitmap1 = Bitmap.createScaledBitmap(listBitmapbm.get(i), convertWidth, convertHighet, true)
            paint4.setColor(Color.BLUE)
            canvas4.drawBitmap(bitmap1, 0F, 0F, null)
            document.finishPage(page4)



            /** fin **/

        }





        // write the document content
        val targetPdf = "/sdcard/"+moduleint+".pdf"
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
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show()

            //   openGeneratedPDF()

    }

    private fun openGeneratedPDF(){
        val file : File = File("/sdcard/moduleISIL.pdf");
        if (file.exists())
        {
            val  intent : Intent = Intent(Intent.ACTION_VIEW);
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
    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun encodePDF(attachment: File): String {
        return Base64.encodeToString(attachment.readBytes(), Base64.DEFAULT)
    }



    private fun decodeImage(base64String : String) : Bitmap {
        val imageBytes : ByteArray = Base64.decode(base64String, Base64.DEFAULT)
        val decodedImage : Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
   return decodedImage
    }







/*
    private fun addFile(id_projet : String , page1 : String , page2: String){

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
                params.put("id_projet",id_projet)
                params.put("page_un","1")
                params.put("page_deux","1")



                return params
            }




        }



        queue.add(req)
        queue.start()


















        /** Fin Req 1 **/
    } */

}
