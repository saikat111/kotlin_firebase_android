package com.xm.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.xm.kotlintutorial.data.User

class AllStudentActivity : AppCompatActivity() {
    private  lateinit var database : DatabaseReference
    private lateinit var userRecy : RecyclerView
    private lateinit var userArrayList : ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        userRecy = findViewById(R.id.r)
        userRecy.layoutManager = LinearLayoutManager(this)
        userRecy.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        getData()
    }
    fun getData(){
        database = FirebaseDatabase.getInstance().getReference("student")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   for(userSnapshot in snapshot.children){
                        val use = userSnapshot.getValue(User::class.java)
                       userArrayList.add(use!!)
                   }
                   userRecy.adapter = MyAdapter(userArrayList)
               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}