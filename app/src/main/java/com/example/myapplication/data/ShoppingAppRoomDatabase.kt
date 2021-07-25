package com.example.myapplication.data

import android.content.Context
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
                ShoppingList(1,"Vegetables", 0),
                ShoppingList(2,"Fruits",1),
                ShoppingList(3, "Tuesday's Dinner", 2)
            )
            val items = mutableListOf(
                Item(1,"Meat",10,1),
                Item(2, "Oranges",2,1),
                Item(3,"Meat",10,2),
                Item(4, "Oranges",2,2),
                Item(5,"Fish",10,3),
                Item(6, "Apples",2,3),
            )
            val dao = instance.shoppingListDao()
            for(i in 0 until items.size){
                val item = items[i]
                dao.insertItem(item)

            }
            dao.insertSingleList(lists[0])
            dao.insertSingleList(lists[1])
            dao.insertSingleList(lists[2])

        }
    }


}