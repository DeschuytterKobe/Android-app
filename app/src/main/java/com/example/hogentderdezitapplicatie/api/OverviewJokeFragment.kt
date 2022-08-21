package com.example.hogentderdezitapplicatie.api

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.databinding.FragmentOverviewJokeBinding
import com.example.hogentderdezitapplicatie.viewmodel.JokeOverviewViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_overview_joke.view.*


class OverviewJokeFragment : Fragment() {

    private lateinit var jokeApiService: JokeApiService

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: JokeOverviewViewModel by lazy {
        ViewModelProvider(this).get(JokeOverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewJokeBinding.inflate(inflater)
        Log.d("in overviewJokeFragment", "yes")
//        val view = inflater.inflate(R.layout.fragment_overview_joke, container, false)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        Log.d("test ", "big zaezaeaze")
        binding.refreshApiCall.setOnClickListener {
            Log.d("test ", "big test")
            viewModel.getJokeProperties()


        }
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel



        setHasOptionsMenu(true)


        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}