package com.example.myapplication.data.entities
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

import java.time.temporal.ChronoUnit

@Parcelize
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) var itemID: Long? = null,
    var itemName: String,
    var itemCount: Int,
    var listID: Int,
) : Parcelable
