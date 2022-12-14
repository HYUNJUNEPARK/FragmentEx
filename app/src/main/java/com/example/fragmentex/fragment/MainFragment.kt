package com.example.fragmentex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentex.MainActivity
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private var mainActivity : MainActivity? = null
    var stringData : String? = null
    var intData : Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mainActivity = context as MainActivity
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = FragmentMainBinding.inflate(inflater, container, false)
            binding.mainFragment = this

            getData()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        return binding.root
    }

    /*
     * getData from bundle of arguments
     */
    private fun getData() {
        stringData = arguments?.getString(getString(R.string.bundleKey_string))
        intData = arguments?.getInt(getString(R.string.bundleKey_int))
    }

    fun onAddToBackStack() {
        mainActivity?.supportFragmentManager!!.beginTransaction().apply {
            add(R.id.fragmentContainerView, StackFragment())
            addToBackStack("detail")
            commit()
        }
    }

    fun setValue(value:String) {
        binding.textFromActivity.text = value
    }
}