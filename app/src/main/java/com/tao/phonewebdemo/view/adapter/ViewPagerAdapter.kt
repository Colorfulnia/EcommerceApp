package com.tao.phonewebdemo.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tao.phonewebdemo.model.remote.data.subcategory.Subcategory


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val subCategoryList: ArrayList<Subcategory>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {

        return AndroidFragment().apply {
            val bundle = Bundle().apply {
                putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id) // key, value
            }
            arguments = bundle
        }
    }

    companion object {
        const val SUB_CATEGORY_ID = "sub_category_id"
    }
}
