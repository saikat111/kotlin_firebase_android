package com.xm.kotlintutorial
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.xm.kotlintutorial.data.User
class MainActivity2 : AppCompatActivity() {
    private lateinit var finddata:Button
    private lateinit var addData:Button
    private lateinit var all:Button
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        finddata = findViewById(R.id.finddata)
        all = findViewById(R.id.all)
        addData = findViewById(R.id.adddata)
        all.setOnClickListener {
            startActivity(Intent(this,AllStudentActivity::class.java))

        }
        addData.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("Enter Roll")
            val dialogLayout = inflater.inflate(R.layout.takeinput, null)
            val name  = dialogLayout.findViewById<EditText>(R.id.name)
            val roll  = dialogLayout.findViewById<EditText>(R.id.roll)
            val phone  = dialogLayout.findViewById<EditText>(R.id.phone)
            val session  = dialogLayout.findViewById<EditText>(R.id.session)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Add") {
                    dialogInterface, i ->
                    addData(name.text.toString(),
                        roll.text.toString(),
                        phone.text.toString(),
                        session.text.toString()
                    )
            }
            builder.show()
        }
        finddata.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("Enter Roll")
            val dialogLayout = inflater.inflate(R.layout.text_inpu_password, null)
            val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Search") {
                    dialogInterface, i ->
                    val intent = Intent(this@MainActivity2,FindActivity::class.java)
                    intent.putExtra("roll",editText.text.toString())
                    startActivity(intent)
            }
            builder.show()
        }
    }
    fun addData(getName:String, getRoll:String, getPhone:String , getSession:String){
        database = FirebaseDatabase.getInstance().getReference("student").child(getRoll)
        val use = User(getName,getPhone,getRoll,getSession)
        database.setValue(use).addOnSuccessListener {
            Toast.makeText(this, "Data save",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Fail",Toast.LENGTH_SHORT).show()
        }
    }
}