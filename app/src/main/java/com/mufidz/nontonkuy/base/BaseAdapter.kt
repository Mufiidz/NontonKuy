package com.mufidz.nontonkuy.base

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Entity : Parcelable, T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemListener: ItemListener<T>? = null

    protected var list: List<Entity> = emptyList()

    override fun getItemCount(): Int = list.size

    abstract fun setData(item: List<Entity>)
}