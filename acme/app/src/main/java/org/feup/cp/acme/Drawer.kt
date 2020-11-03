package org.feup.cp.acme

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class Drawer(private val activity: Activity, actualMenuView: String) {
    private val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawer)
    private val btn = mapOf<String, View>(
        "home" to activity.findViewById(R.id.text_drawer_home),
        "products" to activity.findViewById(R.id.text_drawer_products),
        "account" to activity.findViewById(R.id.text_drawer_account),
        "logout" to activity.findViewById(R.id.text_drawer_logout)
    )

    init {
        activity.findViewById<View>(R.id.btn_burger_menu).setOnClickListener(this::drawerOpen)
        activity.findViewById<View>(R.id.btn_close_drawer).setOnClickListener(this::drawerClose)
        activity.findViewById<View>(R.id.text_drawer_logout).setOnClickListener(this::logoutAction)
        btn["home"]?.setOnClickListener(this::homeRedirect)
        btn["products"]?.setOnClickListener(this::productsRedirect)
        btn["account"]?.setOnClickListener(this::accountRedirect)

        (btn[actualMenuView] as Button).setTextColor(
            ContextCompat.getColor(
                activity,
                R.color.colorLight
            )
        )
        btn[actualMenuView]?.setOnClickListener(null)
    }

    private fun drawerOpen(view: View) {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun drawerClose(view: View) {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun logoutAction(view: View) {
//        TODO("Implement Logout Action")
    }

    private fun homeRedirect(view: View) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }

    private fun productsRedirect(view: View) {
        val intent = Intent(activity, ProductsActivity::class.java)
        activity.startActivity(intent)
    }

    private fun accountRedirect(view: View) {
        val intent = Intent(activity, RegisterActivity::class.java)
        activity.startActivity(intent)
//        TODO("Not yet implemented")
    }
}
