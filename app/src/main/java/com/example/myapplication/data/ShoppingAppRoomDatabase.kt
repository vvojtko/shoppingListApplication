package com.example.myapplication.data

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ShoppingList::class,
        Item::class,

    ],
    version = 1
)

abstract class ShoppingAppRoomDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingAppRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)


        fun getDatabase(context: Context): ShoppingAppRoomDatabase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder<ShoppingAppRoomDatabase>(
                            context.applicationContext,
                            ShoppingAppRoomDatabase::class.java,
                            "shopping_list_database"
                        )
                            .allowMainThreadQueries()
                            .addCallback(roomDatabaseCallback(context))
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .fallbackToDestructiveMigration().build()
                }
                return INSTANCE!!
            }
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch(Dispatchers.IO) {
                        populatedDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }
        private suspend fun populatedDatabase(context: Context, instance: ShoppingAppRoomDatabase){
            val lists = listOf(
                ShoppingList(1,"Warzywa", 0),
                ShoppingList(2,"Owoce",1),
                ShoppingList(3, "obiady", 2)
            )

            val items = mutableListOf(
                Item(1,"mieso",10,1),
                Item(2, "mandarynki",2,1),
                Item(3,"mieso2",10,2),
                Item(4, "mandarynki2",2,2),
                Item(5,"mieso3",10,3),
                Item(6, "mandarynki3",2,3),
            )
            val item = Item(itemName = "kurwa", itemCount = 10, listID = 1)
            val item1 = Item(2,"ez",10,2)
            val item2 = Item(3,"ez",10,3)
            val item3 = Item(4,"ez",10,1)
            val item4 = Item(5,"ez",10,2)
            val item5 = Item(6,"ez",10,3)
            val dao = instance.shoppingListDao()
            dao.insertSingleList(lists[0])
            dao.insertSingleList(lists[1])
            dao.insertSingleList(lists[2])
            dao.insertItem(item5)
            dao.insertItem(item4)
            dao.insertItem(item3)
            dao.insertItem(item1)
            dao.insertItem(item2)
            dao.insertItem(item)

        }
    }


}