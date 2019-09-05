package com.inaki.tmobilecodechallenge.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Model.RepoUserModel
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.DetailsActivity
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.users_items.view.*

class UsersAdapter(private val context: Context,
                   private val listUsers: List<RepoUserModel>,
                   private val userListener: RecyclerOnclick):
RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val userView = LayoutInflater.from(context).inflate(R.layout.users_items,parent,false)
        return UsersViewHolder(userView)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.userName.text = "Username: ${listUsers[position].login}"
        holder.reposNumber.text = "Public Repos: ${listUsers[position].publicRepos}"

        Picasso.get()
            .load(listUsers[position].avatarUrl)
            .resize(50, 50)
            .centerCrop()
            .into(holder.avatar)

        holder.avatar.setOnClickListener {
            val detailsIntent = Intent(context,DetailsActivity::class.java)
            detailsIntent.putExtra("username",holder.userName.text.toString())
            startActivity(context,detailsIntent,null)
            userListener.onClickListener(holder.itemView.user_name.text.toString(),position)
        }
    }

    class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var avatar = itemView.avatar!!
        var userName = itemView.user_name!!
        var reposNumber = itemView.repos_number!!
    }
}