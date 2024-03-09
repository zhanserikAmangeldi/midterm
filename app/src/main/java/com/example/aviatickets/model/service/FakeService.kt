package com.example.aviatickets.model.service

import com.example.aviatickets.model.entity.Airline
import com.example.aviatickets.model.entity.Flight
import com.example.aviatickets.model.entity.Location
import com.example.aviatickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET
import java.util.UUID

interface FakeService {
    @GET("offer_list")
    fun fetchOfferList(): Call<List<Offer>>
}