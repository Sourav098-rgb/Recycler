package com.example.recycler

import android.app.Dialog
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.example.recycler.databinding.ActivityRecycler2Binding

class RecyclerActivity2 : AppCompatActivity(),Onclick {
    lateinit var binding: ActivityRecycler2Binding
    var list = arrayListOf<Data>()
    lateinit var recycleAdapter: Recycle_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecycler2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        list.add(Data("Sourav", 554, "kotlin"))
        list.add(Data("Davinder", 574, "CCNA"))
        list.add(Data("Joshi", 557, "CCNA"))
        recycleAdapter = Recycle_Adapter(list, this)
        binding.Recycler.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.Recycler.adapter = recycleAdapter


        binding.AddFbbtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_add_list)
            val name = dialog.findViewById<TextView>(R.id.Inp_StudentName)
            val rollno = dialog.findViewById<TextView>(R.id.Inp_RollNo)
            val subject = dialog.findViewById<TextView>(R.id.Inp_Subject)
            val btn = dialog.findViewById<Button>(R.id.Add_list)
            dialog.show()

            btn.setOnClickListener {
                val Iname = name.text.toString()
                val IRollno = rollno.text.toString()
                val Isubject = subject.text.toString()

                if (IRollno.isNullOrEmpty() || Iname.isNullOrEmpty() || Isubject.isNullOrEmpty()) {
                    Toast.makeText(this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    list.add(Data(Iname, IRollno.toInt(), Isubject))
                    recycleAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }

        }
    }


    fun Update(position: Int){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.activity_update)
        val name=dialog.findViewById<TextView>(R.id.Inp_StudentName)
        val rollno = dialog.findViewById<EditText>(R.id.InputRollNo)
        val subject = dialog.findViewById<EditText>(R.id.InputSubject)
        val btn = dialog.findViewById<Button>(R.id.Update_list)
        dialog.show()
        btn.setOnClickListener {
            val Irollno=rollno.text.toString()
            val Iname = name.text.toString()
            val Isubject = subject.text.toString()
            if(Irollno.isNullOrEmpty()||Isubject.isNullOrEmpty()||Iname.isNullOrEmpty()){
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }else{
                list.set(position,Data(Iname,Irollno.toInt(),Isubject))
                recycleAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
    }

    override fun update(position: Int) {
        TODO("Not yet implemented")
    }

    override fun delete(position: Int) {
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.activity_alert_dialog)
        val btn = dialog.findViewById<Button>(R.id.Yes)
        val btn2 = dialog.findViewById<Button>(R.id.No)
        dialog.show()
        btn2.setOnClickListener {
            dialog.dismiss()
        }
        btn.setOnClickListener {
            list.removeAt(position)
            recycleAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }

    }
}