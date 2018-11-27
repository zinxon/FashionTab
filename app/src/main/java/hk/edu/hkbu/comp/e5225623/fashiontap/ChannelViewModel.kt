package hk.edu.hkbu.comp.e5225623.fashiontap

import android.databinding.ObservableArrayList
import android.support.annotation.LayoutRes
import me.tatarka.bindingcollectionadapter2.ItemBinding

class ChannelViewModel<T>(variableId:Int, @LayoutRes layoutRes:Int) {
    val items = ObservableArrayList<T>()
    val itemBinding = ItemBinding.of<T>(variableId, layoutRes)
}