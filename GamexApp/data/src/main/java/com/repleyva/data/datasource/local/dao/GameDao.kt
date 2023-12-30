package com.repleyva.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.repleyva.data.datasource.local.tables.GameDbDto
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameDbDto>)

    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<GameDbDto>>

    @Query("SELECT * FROM game WHERE released >= :date ORDER BY rating DESC LIMIT 20 ")
    fun getHotGames(date: Date): Flow<List<GameDbDto>>

    @Query("UPDATE game SET description = :description WHERE id = :id")
    fun updateGameDescription(id: Long, description: String)

    @Query("UPDATE game SET trailer_url = :trailerUrl WHERE id = :id")
    fun updateGameTrailer(id: Long, trailerUrl: String)

    @Query("SELECT * FROM game WHERE name LIKE '%' || :query || '%'")
    fun searchGames(query: String): Flow<List<GameDbDto>>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGameDetails(id: Long): Flow<GameDbDto?>

    @Query("UPDATE game SET is_favorites = :isFavorites WHERE id = :id")
    suspend fun setIsFavorites(isFavorites: Boolean, id: Long)

    @Query("SELECT * FROM game WHERE is_favorites = 1")
    fun getAllFavoriteGames(): Flow<List<GameDbDto>>
}