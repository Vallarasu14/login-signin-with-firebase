package com.example.loginui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Xml
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val arrayAdaptor :ArrayAdapter<*>
        val languages = arrayOf("C","C++","JAVA","PYTHON","JAVASCRIPT","KOTLIN")

        val imageview=arrayOf<Int>(
            R.drawable.clogo,
            R.drawable.cpp,
            R.drawable.javalogo,
            R.drawable.pylogi,
            R.drawable.jslogo,
            R.drawable.kotlogo
        )

        var listview:ListView=findViewById(R.id.listview_id)

        val myListAdapter = MyListAdapter(this,languages,imageview)
        listview.adapter = myListAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        when(item.itemId) {
            R.id.logOut -> {

                val builder=AlertDialog.Builder(this)

                builder.setTitle("Are You Sure")
                builder.setMessage("Do You Want To LogOut?")
                builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->startActivity(Intent(this,MainActivity::class.java)) })
                builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
                builder.show()

            }
            R.id.addAccount -> {
                startActivity(Intent(this,signinActivity::class.java))
            }

            R.id.share -> {
                val intent=Intent()
                val shareBody ="Download next on PlayStore: https://Play.google.com/store/apps/details?id=com.jadebu.nextquiz&h1=en"

                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,shareBody)
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent,"share to: "))
            }

        }
        return super.onOptionsItemSelected(item)

    }

}