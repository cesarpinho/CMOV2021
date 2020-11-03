package org.feup.cp.acme.productTabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.feup.cp.acme.R

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffees, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CoffeesFragment.
         */
        @JvmStatic
        fun newInstance() = CoffeesFragment()
    }
}