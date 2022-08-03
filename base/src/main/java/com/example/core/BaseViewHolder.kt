package com.example.core

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import java.util.HashMap

abstract class BaseViewHolder : RecyclerView.ViewHolder {
    constructor(view : View) : super(view)

    @SuppressLint("UseSparseArrays")
    private val viewHashMap = HashMap<Int, View>()

    fun <T : View> getView(@IdRes id : Int) :  T  {
        var view = viewHashMap[id];
        if (view == null) {
            view = itemView.findViewById(id);
            viewHashMap[id] = view;
        }
        return view as T
    }

    fun setText(@IdRes id: Int, text: String?) {
        (getView<View>(id) as TextView).text = text
    }
}
