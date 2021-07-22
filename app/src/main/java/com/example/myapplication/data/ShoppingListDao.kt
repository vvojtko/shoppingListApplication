package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.data.entities.relations.ShoppingListWithItems

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSingleList(list: ShoppingList)

    @Insert
    fun insertMultipleLists(list: MutableList<ShoppingList>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: Item)

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    fun updateList(list: ShoppingList)

    @Delete
    fun deleteList(list: ShoppingList)

    @Query("Delete FROM shopping_list")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getAllLists(): LiveData<MutableList<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM items WHERE listID = :listID")
    fun getListWithItems(listID: Int): LiveData<MutableList<Item>>
}