package com.example.sampleapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (val id: Long, val name: String, val email: String, val gender: String, val status: String): Parcelable
