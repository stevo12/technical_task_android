package com.sliide.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sliide.myapplication.R
import com.sliide.myapplication.data.UserResponseModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item_list.view.*

class UsersAdapter(private val onLongClick: (UserResponseModel) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var usersList: MutableList<UserResponseModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(usersList[position], onLongClick)
    }

    override fun getItemCount(): Int = usersList.size

    inner class UsersViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: UserResponseModel, onLongClick: (UserResponseModel) -> Unit) {
            containerView.userName.text = item.name
            containerView.userEmail.text = item.email
            containerView.creationTime.text = item.creationTime

            containerView.setOnLongClickListener {
                //TODO complete for delete method
                onLongClick(item)
                true
            }
        }

    }
}