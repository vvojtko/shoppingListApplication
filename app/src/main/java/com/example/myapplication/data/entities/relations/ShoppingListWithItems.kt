package com.example.myapplication.data.entities.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingListWithItems (
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "listID",
        entityColumn = "listID",
    )
    val items: MutableList<Item>
        ) : Parcelable