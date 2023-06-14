package com.example.inhilenttest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val isOfferPrice: Boolean,
    val message: String,
    val value: Double
):Parcelable