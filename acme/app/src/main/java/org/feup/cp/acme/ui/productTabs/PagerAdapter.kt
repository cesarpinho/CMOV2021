package org.feup.cp.acme.productTabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private var fragmentList = mutableListOf<Fragment>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "@drawable/ic_croissant"
    }
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}