package org.feup.cp.acme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.productTabs.CoffeesFragment
import org.feup.cp.acme.ui.productTabs.DrinksFragment
import org.feup.cp.acme.ui.productTabs.PagerAdapter
import org.feup.cp.acme.ui.productTabs.SnacksFragment

class ProductsActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        Drawer(this, "products")

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        getTabs()
    }

    private fun getTabs() {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(CoffeesFragment.newInstance())
        pagerAdapter.addFragment(DrinksFragment.newInstance())
        pagerAdapter.addFragment(SnacksFragment.newInstance())
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_coffee)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_drinks)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_croissant)
    }
}