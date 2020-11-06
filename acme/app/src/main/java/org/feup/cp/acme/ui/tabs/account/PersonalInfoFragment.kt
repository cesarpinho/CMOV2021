package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R

class PersonalInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        TODO("Replace variables for db information")
        val name = "John Costa Lion Doe"
        val nif = 123456789
        val creditCard = 6060589412347676
        val nickname = "zedocafe"

        val view = inflater.inflate(R.layout.fragment_personal_info, container, false)
        view.findViewById<TextView>(R.id.info_name).text = name
        view.findViewById<TextView>(R.id.info_nif).text = nif.toString()
        view.findViewById<TextView>(R.id.info_credit_card).text = creditCard.toString()
        view.findViewById<TextView>(R.id.info_nickname).text = nickname

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonalInfoFragment()
    }
}