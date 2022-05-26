package com.nicootech.multipleviewtypes.ui.adapter.item

import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.recyclerview.widget.RecyclerView
import com.nicootech.multipleviewtypes.R
import java.lang.ref.WeakReference

class TypeBViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var textView: TextView? = null

    var contentB = ""

    var onItemClick : ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView(){
        textView = view.get()?.findViewById(R.id.textView)
    }

    private fun setListener(){
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(contentB)
            }
        }
    }

    fun updateView() {
        textView?.text = contentB
    }
}