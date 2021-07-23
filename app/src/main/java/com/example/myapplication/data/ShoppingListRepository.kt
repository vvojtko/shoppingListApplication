package com.example.myapplication.data

import android.app.Application
import com.example.myapplication.data.ShoppingAppRoomDatabase
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListRepository(application: Application) {
    private val listDao = ShoppingAppRoomDatabase.getDatabase(application)!!.shoppingListDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertItem(item: Item){
        coroutineScope.launch(Dispatchers.IO) {
            listDao.insertItem(item)
        }
    }

    fun insertShoppingList(shoppingList: ShoppingList){
        coroutineScope.launch(Dispatchers.IO) {
            listDao.insertSingleList(shoppingList)
        }
    }

    fun deleteItem(item: Item){
        listDao.deleteItem(item)
    }

    fun deleteList(listID: Int){
        listDao.deleteList(listID)
    }
    fun getAllLists() = listDao.getAllLists()

    fun updateItem(item: Item) = listDao.updateItem(item)

    fun getAllItems(listID: Int) = listDao.getListWithItems(listID)
}