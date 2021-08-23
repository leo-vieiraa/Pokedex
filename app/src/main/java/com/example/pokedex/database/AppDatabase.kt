package com.example.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.database.dao.PokemonDAO
import com.example.pokedex.model.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pokemon_app_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}