package org.feup.cp.acme.ui.tabs.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.feup.cp.acme.R

class PersonalInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PersonalInfoFragment()
    }
}