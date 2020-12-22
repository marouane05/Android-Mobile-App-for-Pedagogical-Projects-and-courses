package com.example.gestiondesprojetspdagogiques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gestiondesprojetspdagogiques.dummy.Comment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import androidx.appcompat.app.AlertDialog
import com.example.gestiondesprojetspdagogiques.dummy.CustomAdapter
import android.widget.LinearLayout.VERTICAL as VERTICAL

class WatchCours : AppCompatActivity() {
    lateinit var videoView: VideoView

    lateinit var vue: TextView
    lateinit var commentaire: EditText
    lateinit var commenter: ImageView
    lateinit var recyclerview: RecyclerView
    lateinit var viewer: ImageView
    lateinit var fram: FrameLayout
    lateinit var lin : LinearLayout
    private var mediaController: MediaController? = null
    private var TAG = "VideoPlayer"
    private val conf: config = config()
    private var Base: String = conf.Base
    private val url: String = Base + "/projet-pedagogique/api/controller/addComment.php"
    private val url2: String = Base + "/projet-pedagogique/api/controller/showComment.php"

    private lateinit var myDialog: AlertDialog
    private val url3: String = Base + "/projet-pedagogique/api/controller/vueCours.php"
    lateinit var back : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_cours)
        var id = intent.getStringExtra("id")
        var filiere = intent.getStringExtra("id_fil")
        var idmoduleChoisi = intent.getStringExtra("id_module")
        var moduleChoisi = intent.getStringExtra("module")

        var Video_url = intent.getStringExtra("vide_url")
        var nom_cours = intent.getStringExtra("nom_cours")
        var id_cours = intent.getStringExtra("id_cours")


        viewer = this.findViewById(R.id.viewer)
        videoView = this.findViewById(R.id.videoView)
        commentaire = this.findViewById(R.id.commentaire)
        vue = this.findViewById(R.id.vue)
        recyclerview = this.findViewById(R.id.recyclerView)
      lin = this.findViewById(R.id.linearLayout1)
        commenter = this.findViewById(R.id.commenter)
        back = this.findViewById(R.id.back)
       videoView.setVideoPath(Video_url)
        //   videoView.setVideoPath("http://192.168.1.9:8080/projet-pedagogique/api/controller/javasript.mp4")

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(TAG, "Duration = " + videoView.duration)
        }
        videoView.start()


        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)




        back.setOnClickListener {



            val intent = Intent(applicationContext, ProfileEtudiant::class.java)
            /* intent.putExtra("email",str_test)
             intent.putExtra("nom",str_nom)
             intent.putExtra("prenom",str_prenom)
             intent.putExtra("profession",professionUser) */

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

        }





        val comment = ArrayList<Comment>()

        /** req 1 **/


        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req = object : StringRequest(Request.Method.POST, url2, Response.Listener<String> {

                response1 ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")
                var str_user: String = ""



                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    comment.add(
                        Comment(
                            jsonInner.get("nom").toString() + " " + jsonInner.get("prenom"),
                            jsonInner.get("commentaire").toString()
                        )
                    )
                }

                val adapter = CustomAdapter(comment)

                //now adding the adapter to recyclerview
                recyclerview.adapter = adapter


            } catch (e: JSONException) {
                e.printStackTrace()
            }
            // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

            //


        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {

                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
                //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
            }
        }) {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {

                val params = HashMap<String, String>()

                params.put("id_cours", id_cours)





                return params
            }


        }



        queue.add(req)
        queue.start()


        /** Fin Req 1 **/

        /** Les visiteurs  **/

        visitors(id_cours, id)

        /** Les visiteurs  **/

        vue.setOnClickListener {

            val myBuilder = AlertDialog.Builder(this)
            //DATA SOURCE
            //   val nebulae = arrayOf<CharSequence>("Boomerang", "Orion", "Witch Head", "Ghost Head", "Black Widow", "Flame", "Cone")
            //SET PROPERTIES USING METHOD CHAINING
            val arr = visitors(id_cours, id).toTypedArray()

            myBuilder.setTitle("Vu par").setItems(arr) { dialogInterface, position ->


                //CREATE DIALOG
                myDialog = myBuilder.create()
                //SHOW DIALOG
                myDialog.show()


            }


        }







            commenter.setOnClickListener {






                /** Req Etudiant 2**/

                var save = commentaire.text.toString()
                commentaire.text.clear()

                val queue = Volley.newRequestQueue(this)


                // Request a string response from the provided URL.
                val req = object : StringRequest(Request.Method.POST, url, Response.Listener<String> {

                        response1 ->
                    try {

                        val obj = JSONObject(response1)
                        val jsonArray: JSONArray = obj.getJSONArray("data")
                        var str_user: String = ""



                        for (i in 0 until jsonArray.length()) {
                            var jsonInner: JSONObject = jsonArray.getJSONObject(i)


                        }


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    // condition si l'utilisateur n'a pas déja activer le compte ou b1 s'il existe comme prof ou admin

                    //


                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {

                        Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
                        //   Toast.makeText(this, "Sorry !! ", Toast.LENGTH_LONG).show()}
                    }
                }) {

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {

                        val params = HashMap<String, String>()

                        params.put("id_cours", id_cours)
                        params.put("id_etudiant", id)
                        params.put("commentaire", save)



                        return params
                    }


                }



                queue.add(req)
                queue.start()


                /** Fin Req 2 **/


            }





    }

    private fun visitors(id_cours: String, id_etudiant: String) : ArrayList<String>{
        val id_visitor :ArrayList<String> = ArrayList<String>()
        val nom_visitor : ArrayList<String> = ArrayList<String>()

        /** Debut request **/



        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val req = object : StringRequest(Request.Method.POST, url3, Response.Listener <String> {

                response1  ->
            try {

                val obj = JSONObject(response1)
                val jsonArray: JSONArray = obj.getJSONArray("data")
                var str_user: String = ""



                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    id_visitor.add(jsonInner.get("id_etudiant").toString())
                    nom_visitor.add(jsonInner.get("nom").toString()+" "+jsonInner.get("prenom").toString())

                }

                vue.text = id_visitor.size.toString() + " VUES"


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

                params.put("id_cours",id_cours)
                params.put("id_etudiant",id_etudiant)




                return params
            }




        }



        queue.add(req)
        queue.start()








        /** Fin request **/

        return nom_visitor
    }


}

