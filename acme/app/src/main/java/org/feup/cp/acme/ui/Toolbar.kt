package org.feup.cp.acme.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import org.feup.cp.acme.R
import org.feup.cp.acme.room.User

class Toolbar(private val activity: Activity, actualMenuView: String?) {
    private val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawer)
    private val cartIndicator: View = activity.findViewById(R.id.cart_selected)
    private val btn = mapOf<String, View>(
        "home" to activity.findViewById(R.id.text_drawer_home),
        "products" to activity.findViewById(R.id.text_drawer_products),
        "account" to activity.findViewById(R.id.text_drawer_account),
        "logout" to activity.findViewById(R.id.text_drawer_logout),
        "cart" to activity.findViewById<View>(R.id.btn_cart)
    )

    init {
        activity.findViewById<View>(R.id.btn_burger_menu).setOnClickListener(this::drawerOpen)
        activity.findViewById<View>(R.id.btn_close_drawer).setOnClickListener(this::drawerClose)
        activity.findViewById<View>(R.id.text_drawer_logout).setOnClickListener(this::logoutAction)
        btn["home"]?.setOnClickListener(this::homeRedirect)
        btn["products"]?.setOnClickListener(this::productsRedirect)
        btn["account"]?.setOnClickListener(this::accountRedirect)
        btn["cart"]?.setOnClickListener(this::cartRedirect)

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

    private fun cartRedirect(view: View) {
        val intent = Intent(activity, CartActivity::class.java)
        activity.startActivity(intent)
    }

    private fun homeRedirect(view: View) {
        val intent = Intent(activity, HomeActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }

    private fun productsRedirect(view: View) {
        val intent = Intent(activity, ProductsActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }

    private fun accountRedirect(view: View) {
        val intent = Intent(activity, AccountActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }

    /**
     * Logout function. Destroys the current user singleton
     * instance.
     */
    private fun logoutAction(view: View) {
        User.destroyUser()
        val intent = Intent(activity, LoginActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }
}
