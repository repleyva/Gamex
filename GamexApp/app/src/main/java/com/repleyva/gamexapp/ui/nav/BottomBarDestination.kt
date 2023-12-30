package com.repleyva.gamexapp.ui.nav

import androidx.annotation.StringRes
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.repleyva.gamexapp.R
import com.repleyva.gamexapp.ui.flow.destinations.BookmarkScreenDestination
import com.repleyva.gamexapp.ui.flow.destinations.HomeScreenDestination
import com.repleyva.gamexapp.ui.flow.destinations.SearchScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    @StringRes val label: Int,
    val icon: Int,
) {
    Home(HomeScreenDestination, R.string.title_home, R.drawable.ic_ghost),
    Search(SearchScreenDestination, R.string.title_search, R.drawable.ic_search),
    Bookmark(BookmarkScreenDestination, R.string.title_bookmark, R.drawable.ic_bookmark),
}