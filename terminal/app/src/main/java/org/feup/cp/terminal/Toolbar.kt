package org.feup.cp.terminal

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class Toolbar(private val activity: Activity, actualMenuView: String?) {

    /**
     * Drawer layout instance
     */
    private val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawer)

    /**
     * Map of names to button instances
     */
    private val btn = mapOf<String, View>(
        "home" to activity.findViewById(R.id.text_drawer_home),
        "qr_code" to activity.findViewById(R.id.text_drawer_qr_code),
        "logout" to activity.findViewById(R.id.text_drawer_logout)
    )

    /**
     * Primary constructor
     * Add listener for all the buttons in the toolbar and the drawer,
     * except the button corresponding to the actual activity
     */
    init {
        activity.findViewById<View>(R.id.btn_burger_menu).setOnClickListener(this::drawerOpen)
        activity.findViewById<View>(R.id.btn_close_drawer).setOnClickListener(this::drawerClose)
        activity.findViewById<View>(R.id.text_drawer_logout).setOnClickListener(this::logoutAction)
        btn["home"]?.setOnClickListener(this::homeRedirect)
        btn["qr_code"]?.setOnClickListener(this::qrCodeRedirect)

        if (actualMenuView != null) {
            (btn[actualMenuView] as Button).setTextColor(
                ContextCompat.getColor(
                    activity,
                    R.color.colorLight
                )
            )
            btn[actualMenuView]?.setOnClickListener(null)
        }
    }

    /**
     * Opens the drawer
     */
    private fun drawerOpen(view: View) {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    /**
     * Closes the drawer
     */
    private fun drawerClose(view: View) {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    /**
     * Redirects to the home activity
     */
    private fun homeRedirect(view: View) {
        val intent = Intent(activity, HomeActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }

    /**
     * Redirects to the QR code reader activity
     */
    private fun qrCodeRedirect(view: View) {
        val intent = Intent(activity, QRCodeActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }

    /**
     * Logout function. Destroys the current user singleton
     * instance.
     */
    private fun logoutAction(view: View) {
//        User.destroyUser()
        // TODO - Implement Logout
        val intent = Intent(activity, LoginActivity::class.java)
        drawerClose(view)
        activity.startActivity(intent)
    }
}
