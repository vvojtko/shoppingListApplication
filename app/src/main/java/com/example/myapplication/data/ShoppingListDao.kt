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

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    fun updateItem(item: Item)

    @Query("Delete FROM shopping_list WHERE listID = :listID")
    fun deleteList(listID: Int)

    @Query("Delete FROM shopping_list")
    fun deleteAll()

    @Delete
    fun deleteItem(item: Item)

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getAllLists(): LiveData<MutableList<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM items WHERE listID = :listID")
    fun getListWithItems(listID: Int): LiveData<MutableList<Item>>
}