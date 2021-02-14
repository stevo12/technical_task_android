package com.sliide.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sliide.myapplication.MainViewModel
import com.sliide.myapplication.R
import com.sliide.myapplication.adapters.UsersAdapter
import com.sliide.myapplication.data.BaseResponseModel
import com.sliide.myapplication.data.UserResponseModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddUserDialog.UserAdded {
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupViews()
        loadData()

        fab.setOnClickListener {
            AddUserDialog.newInstance().show(supportFragmentManager, "new instance")
        }
    }

    private fun setupViews() {
        usersAdapter = UsersAdapter() {
            //logic for delete
        }
        usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = usersAdapter
        }
    }

    private fun loadData() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getUsersListObserver().observe(this, Observer<BaseResponseModel> { response ->
            if (response != null) {
                usersAdapter.usersList = response.data as MutableList<UserResponseModel>
                usersAdapter.notifyDataSetChanged()
            } else {
            }
        })
        viewModel.getUsersList()
    }

    override fun userAdded() {
        loadData()
    }
}