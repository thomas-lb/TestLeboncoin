package com.tlb.testleboncoin.screens

import android.view.View
import com.tlb.testleboncoin.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object AlbumsScreen : Screen<AlbumsScreen>() {
    val recyclerView = KRecyclerView(
        builder = { withId(R.id.albums) },
        itemTypeBuilder = { itemType(AlbumsScreen::Item) }
    )

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val albums = KRecyclerView(
            parent,
            builder = { withId(R.id.items) },
            itemTypeBuilder = { itemType(AlbumsScreen::SimpleItem) }
        )
    }

    class SimpleItem(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val albumId = KTextView(parent) { withId(R.id.album_id) }
    }
}