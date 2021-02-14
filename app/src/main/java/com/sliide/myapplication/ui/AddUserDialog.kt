package com.sliide.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.sliide.myapplication.MainViewModel
import com.sliide.myapplication.R
import com.sliide.myapplication.adapters.UsersAdapter
import com.sliide.myapplication.data.UserResponseModel
import kotlinx.android.synthetic.main.user_add_dialog.*
import java.time.LocalDateTime

class AddUserDialog : DialogFragment() {

    interface UserAdded {
        fun userAdded()
    }

    private lateinit var userAddedInterface: UserAdded
    private lateinit var usersViewModel: MainViewModel
    private lateinit var myAdapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        usersViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.user_add_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = UsersAdapter() {

        }
        addButton.setOnClickListener {
            val userId = usersViewModel.lastUserId
            val userName = nameEditText.text.toString()
            val userEmail = emailEditText.text.toString()
            val getTime = LocalDateTime.now()
            val user = UserResponseModel(
                id = 1,
                name = userName,
                email = userEmail,
                status = "Active",
                gender = "Male",
                creationTime = getTime.toString(),
                updatedTime = getTime.toString()
            )
            usersViewModel.addUser(userResponseModel = user)
            userIsAdded()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userAddedInterface = context as UserAdded
    }

    private fun userIsAdded() {
        userAddedInterface.userAdded()
    }

    companion object {

        @JvmStatic
        fun newInstance(): AddUserDialog = AddUserDialog()
    }

}