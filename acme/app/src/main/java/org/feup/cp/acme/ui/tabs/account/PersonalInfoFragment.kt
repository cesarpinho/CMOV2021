package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R
import org.feup.cp.acme.room.User

class PersonalInfoFragment : Fragment() {

    /**
     * Creates the personal info tab view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val name = User.getInstance()!!.currentUser.name
        val nif = User.getInstance()!!.currentUser.nif
        val creditCard = User.getInstance()!!.currentUser.card
        val nickname = User.getInstance()!!.currentUser.nickname
        val passwordLength = User.getInstance()!!.passwordLength

        // Create the view
        val view = inflater.inflate(R.layout.fragment_personal_info, container, false)

        // Fills the view with the user information
        view.findViewById<TextView>(R.id.info_name).text = name
        view.findViewById<TextView>(R.id.info_nif).text = nif.toString()
        view.findViewById<TextView>(R.id.info_credit_card).text = creditCard.toString()
        view.findViewById<TextView>(R.id.info_nickname).text = nickname
        view.findViewById<TextView>(R.id.info_password).text = "*".repeat(passwordLength)

        return view
    }

    /**
     * Static functions
     */
    companion object {
        @JvmStatic
        fun newInstance() = PersonalInfoFragment()
    }
}