package org.feup.cp.acme.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    /**
     * List of fragment instances present in the pager
     */
    private var fragmentList = mutableListOf<Fragment>()

    /**
     * Returns the quantity of fragments in the pager
     */
    override fun getCount(): Int {
        return fragmentList.size
    }

    /**
     * Returns the fragment in the requested position
     */
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    /**
     * Add a fragment to the pager
     */
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}