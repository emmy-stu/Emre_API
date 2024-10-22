package com.example.emre_api.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.Emre_API.R
import com.example.emre_api.data.LanguageEntity
import com.example.emre_api.recyclerview.LanguageAdapter
import com.example.emre_api.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Purpose:
// - Dashboard fragment to display a list of artworks using a RecyclerView
// - Uses a ViewModel to fetch and observe the list of artworks
// - Binds the artwork data to the RecyclerView using ArtAdapter

// Enable Hilt injection
@AndroidEntryPoint
class FragmentDashboard : Fragment() {

    // Initialized Dependency Injections
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var languageAdapter: LanguageAdapter

    // Use the fragment_dashboard xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    // All the logic happen here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Define the navigation function lambda using safeArgs
        // Lambda is like passing a parameter in Java
        val navigationFunctionLambda: (LanguageEntity) -> Unit = { language ->

            // Use safeArgs to navigate to details fragment, passing the artwork data
            val action = FragmentDashboardDirections.actionDashboardFragmentToDetailsFragment(
                name = language.name,
                family = language.family,
                branch = language.branch,
                speakers = language.speakers,
                writingSystem = language.writingSystem,
                officialIn = language.officialIn.toString(),
                description = language.description
            )

            // Navigate to action (Details)
            findNavController().navigate(action)
        }

        // Initialize recyclerViews from the fragment_dashboard ID
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // initialized artAdapter and pass function navigate lambda when clicked
        languageAdapter = LanguageAdapter(navigationFunction = navigationFunctionLambda)

        // Connect ArtAdapter to RecyclerView to displays data
        recyclerView.adapter = languageAdapter

        // Collect the artworkEntities flow and update RecyclerView
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dashboardViewModel.languageEntity.collect { language ->
                    languageAdapter.submitList(language)
                    // submitList -> in  ArtAdapter
                }
            }
        }

        // Fetch the artworks when the fragment is created
        dashboardViewModel.fetchLanguage()
    }
}

