package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.newsapplication.databinding.BoredApiBinding
import kotlinx.coroutines.launch

class BoredFragment : Fragment() {

    private lateinit var binding: BoredApiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BoredApiBinding.inflate(layoutInflater)
        getBoredData()
        return binding.root
    }

    private fun getBoredData() {
        lifecycleScope.launch {

            binding.apply {
                apiData = com.example.bored_api.Instance.getBoredRepository().getActivity()

                extenalLink.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(extenalLink.text.toString()))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

                buttonRefersh.setOnClickListener {
                    getBoredData()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BoredFragment()
    }
}