package com.example.hogentderdezitapplicatie.fragments.profile


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.ImageSaver
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile_detail.*
import kotlinx.android.synthetic.main.fragment_profile_detail.view.*
import kotlinx.coroutines.launch
import java.util.*


class ProfileDetailFragment : Fragment() {

    private lateinit var uUserViewModel: UserViewModel

    //    private lateinit var currentUser : User
//    private lateinit var button : Button
    private lateinit var imageView: ImageView

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_detail, container, false)
        imageView = view.profile_image_picture
        uUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        var userId = SecureFileHandle(context, AuthTokenSecureFile()).file.userId
        Log.d("test0", "${userId}")

        uUserViewModel.getUserById(userId).observe(viewLifecycleOwner) {

            view.profile_first_name.setText(it.firstName)
            view.profile_last_name.setText(it.lastName)

            val bitmap =
                ImageSaver(context).setFileName(it.profilePhotoUri).setDirectoryName("images")
                    .load()
            imageView.setImageBitmap(bitmap)
        }


        view.profile_image_picture.setOnClickListener {
            pickImageGallery()
        }

        view.btn_update_user.setOnClickListener {
            updateUser()
            it.findNavController().navigate(R.id.action_profileDetailFragment_to_postListFragment2)
        }
        return view

    }

    private fun updateUser() {
        val profileFirstName = profile_first_name.text.toString()
        val profileLastName = profile_last_name.text.toString()
        val bytes = (profile_image_picture.drawable as BitmapDrawable).bitmap


        var filename = UUID.randomUUID().toString();


        ImageSaver(context).setFileName(filename).setDirectoryName("images")
            .save(bytes)
        if (inputCheck(profileFirstName, profileLastName)) {
            lifecycleScope.launch {
                val updateUser =
                    User(
                        SecureFileHandle(context, AuthTokenSecureFile()).file.userId,
                        profileFirstName,
                        profileLastName,
                        1,
                        filename
                    )

                if (updateUser != null) {
                    uUserViewModel.updateUser(updateUser)
                }
            }




            Toast.makeText(requireContext(), "Updated Succesfully!", Toast.LENGTH_SHORT).show()


        } else {
            Toast.makeText(requireContext(), "fill in everything", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lastName))
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK)
            imageView.setImageURI(data?.data)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.myNavHostFragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


}