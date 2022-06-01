package com.example.e_commerceproject.brands.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.e_commerceproject.R
import com.example.e_commerceproject.brands.model.BrandModel
import com.example.e_commerceproject.details.view.DetailsFragment


class BrandsFragment : Fragment(),OnBrandClicked {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brands, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BrandsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }



//    override fun onbrandClicked(model:BrandModel) {
//
////
//    }

    override fun onbrandClicked() {
//        var bundle = Bundle()
//        model.branname?.let { bundle.putString("latitude", it) }
//        model.id?.let { bundle.putInt("120", it) }
//        Toast.makeText(requireContext(),"bhlknlask",Toast.LENGTH_LONG).show()

        val detailsFragment = DetailsFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsFragment)?.commit()
        Toast.makeText(requireContext(),"bhlknlask",Toast.LENGTH_LONG).show()


        //     val df = ()
//        df.arguments = bundle
//        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)?.commit()
//        // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)

    }
}