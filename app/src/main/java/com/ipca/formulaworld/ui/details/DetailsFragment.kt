package com.ipca.formulaworld.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ipca.formulaworld.R

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView = activity?.findViewById<TextView>(R.id.details_title)
        val descriptionTextView = activity?.findViewById<TextView>(R.id.details_description)

        titleTextView?.text = arguments?.getString("title")
        descriptionTextView?.text = arguments?.getString("description")
    }

    companion object {
        fun newInstance(title: String, description: String): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }
}