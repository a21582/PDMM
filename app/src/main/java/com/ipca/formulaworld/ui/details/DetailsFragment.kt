package com.ipca.formulaworld.ui.details

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ipca.formulaworld.MainActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.utils.isNetworkAvailable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

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

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView = activity?.findViewById<ImageView>(R.id.details_image)
        val titleTextView = activity?.findViewById<TextView>(R.id.details_title)
        val descriptionTextView = activity?.findViewById<TextView>(R.id.details_description)

        arguments?.let { args ->
            titleTextView?.text = args.getString("title")
            descriptionTextView?.text = args.getString("description")

            activity?.let { it1 ->
                if (args.containsKey("photo") && isNetworkAvailable(it1.applicationContext)) {
                    GlobalScope.launch {
                        var photoUrl = args.getString("photo").toString()
                        val url = URL(photoUrl)
                        Log.d("TesteNews", photoUrl)
                        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                        activity?.runOnUiThread {
//                            it.image = bmp
                            imageView?.setImageBitmap(bmp)
                        }

                    }
                }
            }
        }

    }

    companion object {
        fun newInstance(title: String, description: String, photo: String): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            args.putString("photo", photo)
            fragment.arguments = args
            return fragment
        }
    }
}