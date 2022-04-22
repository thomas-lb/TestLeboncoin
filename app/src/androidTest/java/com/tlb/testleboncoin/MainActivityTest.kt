package com.tlb.testleboncoin

import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.tlb.testleboncoin.screens.AlbumScreen
import com.tlb.testleboncoin.screens.AlbumsScreen
import org.junit.Assert.*
import org.junit.Test

@LargeTest
class MainActivityTest: TestCase() {

    @Test
    fun navigate() {
        before {
            ActivityScenario.launch(MainActivity::class.java)
        }.after {
        }.run {
            val expected = "Album 1"

            step("Click on the first album") {
                AlbumsScreen {
                    recyclerView.firstChild<AlbumsScreen.Item> {
                        albums.firstChild<AlbumsScreen.SimpleItem> {
                            albumId.containsText(expected)
                            click()
                        }
                    }
                }
            }
            step("Check detail informations") {
                AlbumScreen {
                    recyclerView.firstChild<AlbumScreen.HeaderItem> {
                        name.containsText(expected)
                    }
                }
            }
        }
    }
}