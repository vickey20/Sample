package com.example.vikramgupta.sample.repo.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.DATABASE_NAME

/**
 *   Created by vikramgupta on 10/17/18.
 */

@Database(entities = arrayOf(Country::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance?: synchronized(this) {
                instance?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
        }
    }
}

object DatabaseConstants {
    // Database
    const val DATABASE_NAME = "sample-db"

    // Tables
    const val TABLE_COUNTRIES = "countries"

    // Fields
    const val FAVORITE = "favorite"
    const val NAME = "name"
}