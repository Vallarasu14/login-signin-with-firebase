package com.example.loginui

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.security.AccessControlContext

class MyListAdapter (private val context:Activity,private val languages:Array<String>, private val imageview:Array<Int>)
    :ArrayAdapter<String>(context, R.layout.custom_list, languages){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView =inflater.inflate(R.layout.custom_list,null,true)

        val titletext:TextView=rowView.findViewById(R.id.title)
        val image:ImageView=rowView.findViewById(R.id.icon)

        titletext.text = languages[position]
        image.setImageResource(imageview[position])


        return rowView
    }

    }

