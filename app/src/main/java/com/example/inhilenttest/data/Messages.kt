package com.example.inhilenttest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Messages(
    val promotionalMessage: String,
    val sash: Sash,
    val secondaryMessage: String
):Parcelable