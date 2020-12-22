package com.example.gestiondesprojetspdagogiques

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

public class SessionManager {

lateinit var  pref : SharedPreferences
lateinit var editor : SharedPreferences.Editor
lateinit var  con : Context
    var PRIVATE_MODE : Int = 0


    constructor(con: Context){
        this.con = con
        pref= con.getSharedPreferences(KEY_EMAIL,PRIVATE_MODE)

editor=pref.edit()
    }

companion object{

    val PREF_NAME : String = "Marouane Project"
    val IS_LOGIN : String ="isLoggedIn"
    val KEY_PROFESSION : String ="profession"
    val KEY_EMAIL : String ="email"

}



  fun createLoginSession(profession:String , email : String){

 editor.putBoolean(IS_LOGIN,true)
 editor.putString(KEY_PROFESSION,profession)
 editor.putString(KEY_EMAIL,email)
 editor.commit()


  }



  fun checkLogin(){

      if (!this.isLoggedIn()){
          var i : Intent = Intent(con , MainActivity::class.java)
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
          i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          con.startActivity(i)
      }


  }


  fun getUserDetails() : HashMap<String,String> {

      var user : Map<String,String> = HashMap<String,String>()
      (user as HashMap).put(KEY_PROFESSION,pref.getString(KEY_PROFESSION,null).toString())
      (user as HashMap).put(KEY_EMAIL,pref.getString(KEY_EMAIL,null).toString())

 return user
  }


   fun LogoutUser(){
 editor.clear()
 editor.commit()
       var i : Intent = Intent(con , MainActivity::class.java)
       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
       con.startActivity(i)

   }

  fun isLoggedIn() : Boolean {

      return pref.getBoolean(IS_LOGIN,false)
  }

}


