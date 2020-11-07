package org.feup.cp.acme.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.tabs.PagerAdapter
import org.feup.cp.acme.ui.tabs.account.PersonalInfoFragment
import org.feup.cp.acme.ui.tabs.account.ReceiptFragment
import org.feup.cp.acme.ui.tabs.account.VoucherFragment
import org.feup.cp.acme.ui.tabs.cart.CartListFragment
import org.feup.cp.acme.ui.tabs.cart.QRCodeFragment
import org.feup.cp.acme.ui.tabs.cart.VoucherEntryFragment

class CartActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Toolbar(this, "cart")

        findViewById<View>(R.id.cart_selected).visibility = View.VISIBLE

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        getTabs()
    }

    private fun getTabs() {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(CartListFragment.newInstance())
        pagerAdapter.addFragment(VoucherEntryFragment.newInstance())
        pagerAdapter.addFragment(QRCodeFragment.newInstance())
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_num_1_light)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_num_2_light)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_num_3_light)
    }
}
