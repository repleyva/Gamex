package com.repleyva.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.repleyva.data.datasource.local.dao.GameDao
import com.repleyva.data.datasource.local.tables.GameDbDto

@Database(
    entities = [GameDbDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringTypeConverter::class, DateTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}