package com.example.hogentderdezitapplicatie.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        //view.updateAvatar.setText(args.currentUser.avatar)

        view.update_btn.setOnClickListener{
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)
        return view

    }

    private fun updateItem(){
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val avatar = Integer.parseInt(updateAvatar.text.toString())

        if(inputCheck(firstName,lastName,updateAvatar.text)){
            val updatedUser = User(args.currentUser.id,firstName,lastName,avatar)

            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(),"Succesfully updated",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else
            Toast.makeText(requireContext(),"please fill in everything",Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck(firstName: String, lastName: String, avatar: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && avatar.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){
            _,_ -> mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Succesfully removed: ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("no"){
                _,_ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show(

        )
    }
}