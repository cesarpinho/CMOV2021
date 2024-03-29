package org.feup.cp.acme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.ui.tabs.products.CoffeesFragment
import org.feup.cp.acme.ui.tabs.products.DrinksFragment
import org.feup.cp.acme.ui.tabs.PagerAdapter
import org.feup.cp.acme.ui.tabs.products.SnacksFragment

class ProductsActivity : AppCompatActivity() {

    /**
     * Tab Layout instance
     */
    private lateinit var tabLayout: TabLayout

    /**
     * View pager instance
     */
    private lateinit var viewPager: ViewPager

    /**
     * Creates the account activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        Toolbar(this, "products")

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        getTabs()
    }

    /**
     * Add tabs and icons to the tab bar
     */
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