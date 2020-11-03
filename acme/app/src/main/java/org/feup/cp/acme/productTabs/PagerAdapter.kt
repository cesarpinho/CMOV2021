package org.feup.cp.acme.productTabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter (fm: FragmentManager, private var numOfTabs: Int): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CoffeesFragment()
            1 -> DrinksFragment()
            2 -> SnacksFragment()
            else -> Fragment()
        }
    }

}