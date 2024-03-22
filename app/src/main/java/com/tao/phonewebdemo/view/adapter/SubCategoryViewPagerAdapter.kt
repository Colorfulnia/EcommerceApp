package com.tao.phonewebdemo.view.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.SUB_CATEGORY_ID
import com.tao.phonewebdemo.model.remote.data.subcategory.Subcategory
import com.tao.phonewebdemo.view.fragment.AndroidFragment
import com.tao.phonewebdemo.view.fragment.WindowsFragment
import com.tao.phonewebdemo.view.fragment.iOSFragment


class SubCategoryViewPagerAdapter(fragmentActivity: AppCompatActivity, private val subCategoryList: ArrayList<Subcategory>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when(subCategoryList[position].subcategory_name) {
            "Android" -> AndroidFragment()
            "iOS" -> iOSFragment()
            "Windows" -> WindowsFragment()
            else -> throw IllegalArgumentException("Unknown category")
        }

        fragment.arguments = Bundle().apply {
            putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id) // key, value
        }
        return fragment
    }
}