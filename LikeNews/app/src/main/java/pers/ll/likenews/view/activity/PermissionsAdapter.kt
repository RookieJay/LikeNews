package pers.ll.likenews.view.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pers.ll.likenews.R

class PermissionsAdapter(list: Array<String>) : RecyclerView.Adapter<PermissionsAdapter.PermissionsHolder>() {

    private var mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_permission, parent, false)
        return PermissionsHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: PermissionsHolder, position: Int) {
        val name = mList[position]
        holder.tvPermissionName.text = name
    }

    fun refresh(mPermissions: Array<String>?) {
        mList = mPermissions!!
        notifyDataSetChanged()
    }

    class PermissionsHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvPermissionName : TextView = view.findViewById(R.id.tvPermissionName)
    }
}