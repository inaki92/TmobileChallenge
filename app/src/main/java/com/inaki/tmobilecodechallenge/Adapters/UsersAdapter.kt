package com.inaki.tmobilecodechallenge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Model.Users.RepoUserModel
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.users_items.view.*

class UsersAdapter(private val context: Context,
                   private val listUsers: List<RepoUserModel>,
                   private val userListener: RecyclerOnclick):
RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val userView = LayoutInflater.from(context).inflate(R.layout.users_items,parent,false)
        return UsersViewHolder(userView,userListener)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.userName.text = listUsers[position].login
        holder.reposNumber.text = listUsers[position].publicRepos.toString()

        Picasso.get()
            .load(listUsers[position].avatarUrl)
            .resize(50,50)
            .centerCrop()
            .into(holder.avatar)
    }

    class UsersViewHolder(itemView: View, userListener: RecyclerOnclick):
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var onClickListener: RecyclerOnclick

        init {
            itemView.setOnClickListener(this)
            onClickListener = userListener
        }

        var avatar = itemView.avatar!!
        var userName = itemView.user_name!!
        var reposNumber = itemView.repos_number!!

        override fun onClick(v: View?) {
            onClickListener.onClickListener(userName.text.toString(),adapterPosition)
        }
    }
}