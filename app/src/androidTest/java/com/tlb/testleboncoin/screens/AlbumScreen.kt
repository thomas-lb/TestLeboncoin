package com.tlb.testleboncoin.screens

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.tlb.testleboncoin.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object AlbumScreen: Screen<AlbumScreen>() {
    val recyclerView = KRecyclerView(
        builder = { withId(R.id.titles) },
        itemTypeBuilder = { itemType(AlbumScreen::HeaderItem) }
    )

    class HeaderItem(parent: Matcher<View>): KRecyclerItem<AlbumsScreen.Item>(parent) {
        val name = KTextView(parent) { withId(R.id.name) }
    }
}