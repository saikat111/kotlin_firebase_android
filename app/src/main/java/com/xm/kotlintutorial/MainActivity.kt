package com.xm.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.xm.kotlintutorial.data.User

class MainActivity : AppCompatActivity() {
/*
    private lateinit var binding: ActivityMainBinding
*/
    private lateinit var database :DatabaseReference
    private  lateinit var name : EditText
    private  lateinit var save : Button
    private  lateinit var sname : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     /*   binding = ActivityMainBinding.inflate(layoutInflater)*/
        setContentView(R.layout.activity_main)
        name = findViewById<EditText>(R.id.name)
        save = findViewById<Button>(R.id.save)
        sname = findViewById<TextView>(R.id.sname)
        getData()
        name.setOnClickListener {
            name.text.clear()
        }
        save.setOnClickListener {
            var text = name.text.toString()
           /* sname.text = text
            Toast.makeText(this, text,Toast.LENGTH_SHORT).show()*/
            database = FirebaseDatabase.getInstance().getReference("profile").child("name")
            val use = User(text)
            database.setValue(use).addOnSuccessListener {
                name.text.clear()
                Toast.makeText(this, "Data save",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Fail",Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun getData(){
        database = FirebaseDatabase.getInstance().getReference("profile").child("name")
        //get data one's
      /*  database.get().addOnSuccessListener {
            if (it.exists()){
                var getName = it.child("name").value
                sname.text = getName as CharSequence?
            }
            else{
                Toast.makeText(this, "no user",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Fail",Toast.LENGTH_SHORT).show()
        }*/
        //get data every change
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var getName = snapshot.child("name").value
                    sname.text = getName as CharSequence?
                }
                else{

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}