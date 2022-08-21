package com.example.hogentderdezitapplicatie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


//         setHasOptionsMenu(true)
        return view

    }

//         override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//             super.onCreateOptionsMenu(menu, inflater)
//             inflater?.inflate(R.menu.overflow_menu,menu)
//         }
//
//         override fun onOptionsItemSelected(item: MenuItem): Boolean {
//             return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())||
//                     return super.onOptionsItemSelected(item)
//         }
}