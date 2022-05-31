package com.example.e_commerceproject.Settings.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.e_commerceproject.R
class SettingsFragment : Fragment() {

    lateinit var backArrow: ImageView
    lateinit var settingsFragmentView: View
    lateinit var addressArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsFragmentView = inflater.inflate(R.layout.fragment_settings, container, false)
        return settingsFragmentView
        // Inflate the layout for this fragment

    }
    /// address toast
    // back setting arrow profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backArrow= settingsFragmentView.findViewById(R.id.settingArrowBack)
        backArrow.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            //  fragmentTransaction.replace(R.id.,fragment)
            fragmentTransaction.commit()
        })
        addressArrow= settingsFragmentView.findViewById(R.id.navigateToAddressScreenBtn)
        addressArrow.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            //  fragmentTransaction.replace(R.id.,fragment)
            fragmentTransaction.commit()
        })

    }


}
