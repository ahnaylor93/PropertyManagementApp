package com.example.propertymanagementapp.sql

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.propertymanagementapp.sql.dao.TodoDao


@Database(
    entities = [Todo::class],
    version =1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object{
        private  lateinit var db:AppDatabase
        fun getInstance(context: Context):AppDatabase{
            if(!this::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "TodoDB")
                    .allowMainThreadQueries()
                    .build()

            }
            return db
        }
    }
}