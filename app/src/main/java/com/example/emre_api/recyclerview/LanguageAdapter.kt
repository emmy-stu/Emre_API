package com.example.emre_api.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.Emre_API.R
import com.example.emre_api.data.LanguageEntity
import com.example.emre_api.recyclerview.LanguageViewHolder

// Purpose:
// - Adapter to handle the display of language data in a RecyclerView
// - Responsible for creating ViewHolder instances and binding data to the RecyclerView

// Implementations:
// - RecyclerView xml layout (fragment_dashboard)
// - xml layout for ViewHolder (item_layout_restful_api_service xml)
// - Create Adapter
// - Create ViewHolder class
// - ViewModel -> to manage data flow
// - Set data in fragment

// Adapter class to manage the languages data
class LanguageAdapter(
    private var languageList: List<LanguageEntity> = emptyList(),

    private val navigationFunction: (LanguageEntity) -> Unit) :

    RecyclerView.Adapter<LanguageViewHolder>() {

    // Creates new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        // Using the item_layout_restful_api_service xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return LanguageViewHolder(view)
    }

    // Binds the data to ViewHolder
    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        // Fetch the language item index
        val language = languageList[position]
        // Call bind to set the item data
        holder.bind(language)

        // Find the button in the item layout and set a click listener
        holder.itemView.findViewById<Button>(R.id.goToDetails).setOnClickListener {
            navigationFunction(language) // Pass the clicked language to the lambda
        }
    }

    // Returns a total number of the items
    override fun getItemCount(): Int {
        return languageList.size
    }

    // Update the list of language entities and notify the adapter
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(language: List<LanguageEntity>) {
        languageList = language
        notifyDataSetChanged()
    }
}
