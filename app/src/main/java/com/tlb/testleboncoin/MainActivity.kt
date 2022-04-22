package com.tlb.testleboncoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tlb.testleboncoin.albums.AlbumsFragmentDirections
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}