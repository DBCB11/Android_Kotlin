package com.example.emailapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emailapp.R
import com.example.myapp.models.Email

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAvatar: TextView = itemView.findViewById(R.id.tvAvatar)
        val tvSender: TextView = itemView.findViewById(R.id.tvSender)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvSnippet: TextView = itemView.findViewById(R.id.tvSnippet)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.tvAvatar.text = email.avatar
        holder.tvSender.text = email.sender
        holder.tvSubject.text = email.subject
        holder.tvSnippet.text = email.snippet
        holder.tvTime.text = email.time
    }

    override fun getItemCount(): Int = emailList.size
}
