package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    /**  var declarations **/

    lateinit var logBtn : Button
    lateinit var activerBtn : TextView
    lateinit var  email : EditText
    lateinit var password : EditText

    lateinit var estee: ImageView
    lateinit var fb : ImageView
    lateinit var uca : ImageView
lateinit var linekdin : ImageView
    lateinit var session : SessionManager
    val conf : config=config()
    var Base :String= conf.Base
    val url = Base+"/projet-pedagogique/api/controller/login.php"




    /**  var declarations **/





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        estee = this.findViewById(R.id.estesite)
        fb = this.findViewById(R.id.fb)
        uca = this.findViewById(R.id.uca)
        linekdin = this.findViewById(R.id.linekdin)
        /**  var declarations  && initialisation**/
        var reponse : String =""
        var trouve : String ="succed"
        var profession : String =""
        val conf : config=config()
        logBtn= this.findViewById(R.id.btnCreer)
        activerBtn = this.findViewById(R.id.activer)
        val jsonOb= JSONObject()
        val jsonArr = JSONArray()

        /**  var declarations  aa initialisation**/

/** session initialisation && condition**/
        session = SessionManager(applicationContext)




/**  ************ **/


        estee.setOnClickListener {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("http://www.este.ucam.ac.ma/")
            startActivity(openURL)


        }

        fb.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.facebook.com/groups/este.essaouira")
            startActivity(openURL)

        }

        uca.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.uca.ma/")
            startActivity(openURL)

        }

        linekdin.setOnClickListener {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.linkedin.com/company/cadi-ayyad-university/")
            startActivity(openURL)

        }

logBtn.setOnClickListener {




    val email = Email.text.toString().trim()
    val password = Password.text.toString().trim()
    var str_test : String =" "
    if(email.isEmpty()){
       Email.error = "Email required"
 Email.requestFocus()
        return@setOnClickListener
    }


    if(password.isEmpty()){
     Password.error = "Password required"
     Password.requestFocus()
        return@setOnClickListener
    }

   /* jsonOb.put("email",email)
    jsonOb.put("password",password)
*/

    // 1 comment supprimé code volley

    val queue = Volley.newRequestQueue(this@MainActivity)
    val req = object : StringRequest (Request.Method.POST,url, Response.Listener <String> {

            response  ->
        try {

       val obj = JSONObject(response)
            val jsonArray: JSONArray = obj.getJSONArray("reponse")
            var str_user: String = ""

            for (i in 0 until jsonArray.length()) {
                var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                str_user = str_user + "\n" + jsonInner.get("succed") +"\n" +jsonInner.get("loggedin")
                str_test =jsonInner.get("loggedin").toString()
                profession =jsonInner.get("profession").toString()
            }


            Toast.makeText(this, str_user, Toast.LENGTH_LONG).show()

        } catch (e: JSONException){ e.printStackTrace()}
if(str_test.trim().equals("true")) {


    session.createLoginSession(profession,email)


        if(session.isLoggedIn()){

            if(profession.equals("Professeur")){

            var i : Intent = Intent(applicationContext, ProfileActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()}
            if(profession.equals("Administrateur")){



                var i : Intent = Intent(applicationContext, ProfileAdmin::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()

            }

            if(profession.equals("Etudiant")){



                var i : Intent = Intent(applicationContext, ProfileEtudiant::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()

            }

        }




} else {
    Toast.makeText(this,"mot de passe incorrecte", Toast.LENGTH_LONG).show()
}
    },object : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {

            Toast.makeText(applicationContext,"sorry", Toast.LENGTH_LONG).show()
            //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
        }
    })   {

        @Throws(AuthFailureError::class)
        override fun getParams(): Map<String, String> {

            val params = HashMap<String,String>()
            params.put("email",email)
            params.put("password",password)
            return params
        }

    }



    queue.add(req)
    queue.start()










}

activerBtn.setOnClickListener {

    val intent = Intent(applicationContext, ActiverCompte::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    startActivity(intent)


}




}

    }


























/*

    fun BuGetSunRiseTimeEvent(view: View){



        val url=""
MyAsyncTask().execute(url)
    }



    inner class MyAsyncTask:AsyncTask<String,String,String>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            try {
                val url= URL(p0[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout= 700
                val dataJsonAsStr = convertStreamToString(urlConnect.inputStream)
                publishProgress(dataJsonAsStr)
            } catch (ex: Exception){

            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {

// access Ui
            super.onProgressUpdate(*values)
            val json = JSONObject(values[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }


    fun convertStreamToString(inputStream:InputStream):String{
val bufferedReader = BufferedReader(InputStreamReader(inputStream))
var line : String
var allString : String =""

        do{
            line = bufferedReader.readLine()
            if (line != null){
                allString += line
            }
       }while (line !=null)
    return allString
    }


*/






