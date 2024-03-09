package com.example.aviatickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.service.FakeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private var adapter: OfferListAdapter? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OfferListAdapter()
        binding.offerList.adapter = adapter
        var offerList = ApiClient.instance
        var response = offerList.fetchOfferList()

        response.enqueue(object : Callback<List<Offer>>{
            override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
                println("HttpResponse: ${response.body()}")


                val offers = response.body()
                println(offers)

                if (offers!= null) {
                    setupUI()
                    adapter?.setItems(offerList = offers)

                }
            }

            override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
                println("HttpResponse: ${t.message}")
            }
        }
        )

    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                    }
                }
            }
        }
    }
}