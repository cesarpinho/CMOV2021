package org.feup.cp.acme.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.feup.cp.acme.ui.tabs.cart.QRCodeFragment

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

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
     * Returns the position of a object.
     * If the object is a QRCodeFragment instance returns POSITION_NONE to ensure that this fragment is reloaded
     */
    override fun getItemPosition(`object`: Any): Int {
        if (`object` is QRCodeFragment)
            return POSITION_NONE

        return super.getItemPosition(`object`)
    }

    /**
     * Add a fragment to the pager
     */
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}