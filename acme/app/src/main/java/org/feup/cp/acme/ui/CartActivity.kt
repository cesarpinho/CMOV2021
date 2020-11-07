package org.feup.cp.acme.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.tabs.PagerAdapter
import org.feup.cp.acme.ui.tabs.cart.CartListFragment
import org.feup.cp.acme.ui.tabs.cart.QRCodeFragment
import org.feup.cp.acme.ui.tabs.cart.VoucherEntryFragment

class CartActivity : AppCompatActivity() {

    /**
     * Tab Layout instance
     */
    private lateinit var tabLayout: TabLayout

    /**
     * View pager instance
     */
    private lateinit var viewPager: ViewPager

    /**
     * Pager adapter instance
     */
    private lateinit var pagerAdapter: PagerAdapter

    /**
     * Voucher tab lock indicator
     */
    private var lockVoucher = true

    /**
     * QRCode tab lock indicator
     */
    private var lockQRCode = true

    /**
     * Creates the cart activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Toolbar(this, "cart")

        findViewById<View>(R.id.cart_selected).visibility = View.VISIBLE

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)
        pagerAdapter = PagerAdapter(supportFragmentManager)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        addCartListTab()
    }

    /**
     * Add cart list tab to the tab bar
     */
    private fun addCartListTab() {
        pagerAdapter.addFragment(CartListFragment.newInstance(this))
        pagerAdapter.notifyDataSetChanged()
        addTabIcons()
    }

    /**
     * Add voucher tab to the tab bar
     */
    fun addVoucherTab() {
        if (lockVoucher) {
            pagerAdapter.addFragment(VoucherEntryFragment.newInstance(this))
            pagerAdapter.notifyDataSetChanged()
            addTabIcons()
            lockVoucher = false
        }
        viewPager.setCurrentItem(1, true)
    }

    /**
     * Add qr code tab to the tab bar
     */
    fun addQRCodeTab() {
        if (lockQRCode) {
            pagerAdapter.addFragment(QRCodeFragment.newInstance())
            pagerAdapter.notifyDataSetChanged()
            addTabIcons()
            lockQRCode = false
        }
        viewPager.setCurrentItem(2, true)
    }

    /**
     * Add the tab icons when a new tab is added
     */
    private fun addTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_num_1_light)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_num_2_light)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_num_3_light)
    }
}
