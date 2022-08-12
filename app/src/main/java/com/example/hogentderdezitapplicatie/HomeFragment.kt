package com.example.hogentderdezitapplicatie

import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import kotlinx.coroutines.launch


class HomeFragment : Fragment()
     {

      override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_home, container, false)


         setHasOptionsMenu(true)
         return view

     }

         override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
             super.onCreateOptionsMenu(menu, inflater)
             inflater?.inflate(R.menu.overflow_menu,menu)
         }

         override fun onOptionsItemSelected(item: MenuItem): Boolean {
             return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())||
                     return super.onOptionsItemSelected(item)
         }
}