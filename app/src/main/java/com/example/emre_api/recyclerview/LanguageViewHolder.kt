package com.example.emre_api.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Emre_API.R
import com.example.emre_api.data.LanguageEntity

// Purpose:
// - Holds references to the views for each RecyclerView item
// - Binds artwork data to these views

// ViewHolder class to hold references to views for each item
class LanguageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Right: is from the item_layout_restful_api_dev
    // Left: is a declared values
    private val name: TextView = view.findViewById(R.id.name)
    private val family: TextView = view.findViewById(R.id.family)
    private val speakers: TextView = view.findViewById(R.id.speakers)
    private val branch: TextView = view.findViewById(R.id.branch)
    private val officialIn: TextView = view.findViewById(R.id.officialIn)
    private val description: TextView = view.findViewById(R.id.description)

    // Bind the data to the views
    @SuppressLint("SetTextI18n")

    // Right: is from the LanguageEntity Class
    // Left: if from initialize item up there
    fun bind(language: LanguageEntity) {
        name.text = language.name
        family.text = language.family
        branch.text = language.branch
        speakers.text = language.speakers.toString()
        officialIn.text = language.officialIn.toString()
        description.text = language.description
    }
}