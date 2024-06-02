package com.example.userblink_itclone

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.userblink_itclone.databinding.ProgressDialogBinding
import com.google.firebase.auth.FirebaseAuth

object Utils {
    private var dialog : AlertDialog? = null

fun showDialog(context: Context,message: String){
    val progress = ProgressDialogBinding.inflate(LayoutInflater.from(context))
    dialog = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
    dialog!!.show()
}
    fun hideDialog(){
        dialog?.dismiss()
    }
    fun showToast(context: Context,message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    private var firebaseAuthInstance:FirebaseAuth? = null
    fun getAuthInstance():FirebaseAuth{
        if (firebaseAuthInstance==null){
            firebaseAuthInstance = FirebaseAuth.getInstance()
        }
        return firebaseAuthInstance!!
    }


}