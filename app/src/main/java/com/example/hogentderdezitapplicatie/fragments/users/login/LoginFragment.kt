package com.example.hogentderdezitapplicatie.fragments.users.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.activies.PostActivity
import com.example.hogentderdezitapplicatie.databinding.FragmentLoginBinding
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : AppCompatActivity() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var mPostViewModel: PostViewModel

    // Login/logout-related properties
    private lateinit var account: Auth0
    private var cachedCredentials: Credentials? = null
    private var cachedUserProfile: UserProfile? = null
    private var loggedin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        if (loggedin) logout()
        else {
            account = Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain)
            )

            binding = FragmentLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.buttonLogout.isEnabled = true
            binding.buttonLogin.setOnClickListener { login(1) }
            binding.buttonLogin1.setOnClickListener { login(2) }
            binding.buttonLoginAdmin.setOnClickListener { login(3) }
            binding.buttonLogout.setOnClickListener { logout() }
            binding.buttonGet.setOnClickListener { getUserMetadata() }
            binding.buttonSet.setOnClickListener { setUserMetadata() }
        }


    }

    private fun login(userId: Int) {
        WebAuthProvider
            .login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .withScope(getString(R.string.login_scopes))
            .withAudience(getString(R.string.login_audience, getString(R.string.com_auth0_domain)))
            .start(this, object : Callback<Credentials, AuthenticationException> {

                override fun onFailure(exception: AuthenticationException) {
                    showSnackBar(getString(R.string.login_failure_message, exception.getCode()))
                }

                override fun onSuccess(credentials: Credentials) {
                    cachedCredentials = credentials
                    val isLoggedIn = cachedCredentials != null
                    loggedin = true
                    binding.buttonLogout.isEnabled = isLoggedIn
                    showSnackBar(getString(R.string.login_success_message, credentials.accessToken))

                    val securefile = SecureFileHandle(applicationContext, AuthTokenSecureFile())

                    securefile.file.fill(cachedCredentials, userId);

                    val navigateToPostActivity =
                        Intent(applicationContext, PostActivity::class.java)
                    securefile.saveFile()




                    startActivity(navigateToPostActivity)

//                    updateUI()
//                    showUserProfile()


                }
            })
    }


    private fun logout() {
        //TODO
        val securefile = SecureFileHandle(applicationContext, AuthTokenSecureFile())
        securefile.clearFile()

        WebAuthProvider
            .logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Void?, AuthenticationException> {

                override fun onFailure(exception: AuthenticationException) {
                    updateUI()
                    showSnackBar(
                        getString(
                            R.string.general_failure_with_exception_code,
                            exception.getCode()
                        )
                    )
                }

                override fun onSuccess(payload: Void?) {
                    cachedCredentials = null
                    cachedUserProfile = null
                    updateUI()

                }

            })
    }

    private fun showUserProfile() {
        // Guard against showing the profile when no user is logged in
        if (cachedCredentials == null) {
            return
        }

        val client = AuthenticationAPIClient(account)
        client
            .userInfo(cachedCredentials!!.accessToken!!)
            .start(object : Callback<UserProfile, AuthenticationException> {

                override fun onFailure(exception: AuthenticationException) {
                    showSnackBar(
                        getString(
                            R.string.general_failure_with_exception_code,
                            exception.getCode()
                        )
                    )
                }

                override fun onSuccess(profile: UserProfile) {
                    cachedUserProfile = profile
                    updateUI()
                }

            })
    }

    private fun getUserMetadata() {
        // Guard against getting the metadata when no user is logged in
        if (cachedCredentials == null) {
            return
        }

        val usersClient = UsersAPIClient(account, cachedCredentials!!.accessToken!!)

        usersClient
            .getProfile(cachedUserProfile!!.getId()!!)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(exception: ManagementException) {
                    showSnackBar(
                        getString(
                            R.string.general_failure_with_exception_code,
                            exception.getCode()
                        )
                    )
                }

                override fun onSuccess(userProfile: UserProfile) {
                    cachedUserProfile = userProfile
                    updateUI()

                    val country = userProfile.getUserMetadata()["country"] as String?
                    binding.edittextCountry.setText(country)
                }

            })
    }

    private fun setUserMetadata() {
        // Guard against getting the metadata when no user is logged in
        if (cachedCredentials == null) {
            return
        }

        val usersClient = UsersAPIClient(account, cachedCredentials!!.accessToken!!)
        val metadata = mapOf("country" to binding.edittextCountry.text.toString())

        usersClient
            .updateMetadata(cachedUserProfile!!.getId()!!, metadata)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(exception: ManagementException) {
                    showSnackBar(
                        getString(
                            R.string.general_failure_with_exception_code,
                            exception.getCode()
                        )
                    )
                }

                override fun onSuccess(profile: UserProfile) {
                    cachedUserProfile = profile
                    updateUI()

                    showSnackBar(getString(R.string.general_success_message))
                }

            })
    }

    private fun updateUI() {
        val isLoggedIn = cachedCredentials != null

        binding.textviewTitle.text = if (isLoggedIn) {
            getString(R.string.logged_in_title)
        } else {
            getString(R.string.logged_out_title)
        }
        binding.buttonLogin.isEnabled = !isLoggedIn
        binding.buttonLogout.isEnabled = isLoggedIn
        binding.linearlayoutMetadata.isVisible = isLoggedIn

        binding.textviewUserProfile.isVisible = isLoggedIn

        val userName = cachedUserProfile?.name ?: ""
        val userEmail = cachedUserProfile?.email ?: ""
        binding.textviewUserProfile.text = getString(R.string.user_profile, userName, userEmail)

        if (!isLoggedIn) {
            binding.edittextCountry.setText("")
        }

    }

    private fun showSnackBar(text: String) {
        Snackbar
            .make(
                binding.root,
                text,
                Snackbar.LENGTH_LONG
            ).show()
    }
}