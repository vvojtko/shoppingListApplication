package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.data.ShoppingAppRoomDatabase
import com.example.myapplication.data.ShoppingListDao
import com.example.myapplication.data.entities.Item
import com.example.myapplication.data.entities.ShoppingList
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.CardItemBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var listCardItemBinding: CardItemBinding
    private lateinit var binding2: ShoppingListFragment
    private lateinit var shoppingListsList: ArrayList<ShoppingList>

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
         Log.d(TAG, "essa")

        val navView = binding.bottomNavMenu
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)


        //val dao:ShoppingListDao=ShoppingAppRoomDatabase.getInstance(this).shoppingListDao()


        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_shopping, R.id.archivedShoppingFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

}