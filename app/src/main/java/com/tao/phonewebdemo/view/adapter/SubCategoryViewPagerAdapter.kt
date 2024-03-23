package com.tao.phonewebdemo.view.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.SUB_CATEGORY_ID
import com.tao.phonewebdemo.model.remote.data.subcategory.Subcategory
import com.tao.phonewebdemo.view.fragment.SubcategoryItemsFragment


class SubCategoryViewPagerAdapter(activity: AppCompatActivity, private val subCategoryList: ArrayList<Subcategory>) : FragmentStatePagerAdapter(activity.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return subCategoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return SubcategoryItemsFragment().apply {
            val bundle = Bundle(1)
            bundle.putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id)
            arguments = bundle
        }
    }
}
