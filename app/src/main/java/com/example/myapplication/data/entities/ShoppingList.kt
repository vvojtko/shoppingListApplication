package com.example.myapplication.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.entities.Item
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "shopping_list")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) var listID:  Int? = null,
    var shoppingListName: String,
    //var list: @RawValue MutableList<Item>,
    var progress: Int,
) : Parcelable {
}
