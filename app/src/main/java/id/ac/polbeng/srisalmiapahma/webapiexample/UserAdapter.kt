package id.ac.polbeng.srisalmiapahma.webapiexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.polbeng.srisalmiapahma.webapiexample.databinding.ItemUserBinding

class UserAdapter(private val listUser: List<DataItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        holder.binding.tvName.text = "${user.firstName} ${user.lastName}"
        holder.binding.tvEmail.text = user.email
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.binding.ivAvatar)
    }

    override fun getItemCount(): Int = listUser.size

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}
