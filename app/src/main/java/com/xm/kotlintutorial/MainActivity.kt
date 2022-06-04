package com.xm.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database :DatabaseReference
    private  lateinit var name : EditText
    private  lateinit var save : Button
    private  lateinit var sname : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById<EditText>(R.id.name)
        save = findViewById<Button>(R.id.save)
        sname = findViewById<TextView>(R.id.sname)
        getData()
        name.setOnClickListener {
            name.text.clear()
        }
        save.setOnClickListener {
            var text = name.text
            sname.text = text
            Toast.makeText(this, text,Toast.LENGTH_SHORT).show()
        }
    }
    fun getData(){
        database = FirebaseDatabase.getInstance().getReference("profile")
        database.get().addOnSuccessListener {
            if (it.exists()){
                var getName = it.child("name").value
                sname.text = getName as CharSequence?
            }
            else{
                Toast.makeText(this, "no user",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Fail",Toast.LENGTH_SHORT).show()
        }

    }
}