package com.xm.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class FindActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var name:TextView
    private lateinit var roll:TextView
    private lateinit var se:TextView
    private lateinit var pn:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        name = findViewById(R.id.name)
        roll = findViewById(R.id.roll)
        pn = findViewById(R.id.pn)
        se = findViewById(R.id.se)
        val profileRoll=intent.getStringExtra("roll")
        if (profileRoll != null) {
            getData(profileRoll)
        }
    }
    fun getData(rollNumber: String){
        database = FirebaseDatabase.getInstance().getReference("student").child(rollNumber)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var getName = snapshot.child("name").value
                    name.text = getName as CharSequence?
                    var getRoll = snapshot.child("roll").value
                    roll.text = getRoll as CharSequence?
                    var getph = snapshot.child("phone").value
                    pn.text = getph as CharSequence?
                    var getse = snapshot.child("session").value
                    se.text = getse as CharSequence?
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