package com.example.inhilenttest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PurchaseType(
    val cartQty: Int,
    val displayName: String,
    val maxQtyLimit: Int,
    val minQtyLimit: Int,
    val purchaseType: String,
    val unitPrice: Double
):Parcelable