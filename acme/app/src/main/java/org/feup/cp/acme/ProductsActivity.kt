package org.feup.cp.acme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import org.feup.cp.acme.productTabs.PagerAdapter

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        Drawer(this, "products")

        val tabLayout: TabLayout = findViewById(R.id.tabs)
//        var tabCoffees: TabItem = findViewById(R.id.tab_coffees)
//        var tabDrinks: TabItem = findViewById(R.id.tab_drinks)
//        var tabSnacks: TabItem = findViewById(R.id.tab_snacks)
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        val pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager.currentItem = p0!!.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }
}