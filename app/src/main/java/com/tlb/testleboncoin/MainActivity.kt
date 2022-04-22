package com.tlb.testleboncoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.tlb.testleboncoin.albums.AlbumsFragmentDirections
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity: AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private val configuration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(navController, configuration)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(configuration)
            || super.onSupportNavigateUp()
}