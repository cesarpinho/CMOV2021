package org.feup.cp.acme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.tabs.PagerAdapter
import org.feup.cp.acme.ui.tabs.account.PersonalInfoFragment
import org.feup.cp.acme.ui.tabs.account.ReceiptFragment
import org.feup.cp.acme.ui.tabs.account.VoucherFragment

class AccountActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        Drawer(this, "account")

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        getTabs()
    }

    private fun getTabs() {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(PersonalInfoFragment.newInstance())
        pagerAdapter.addFragment(ReceiptFragment.newInstance())
        pagerAdapter.addFragment(VoucherFragment.newInstance())
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_personal_info)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_receipt)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_voucher)
    }
}