package com.example.androidlearning.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidlearning.dao.TodoDao
import com.example.androidlearning.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
                    ).build()
                }
                return instance!!
            }
        }
    }
}
/*@Volatile
changes made by one thread to a shared variable may not be immediately visible to other threads.
 This can lead to unexpected and difficult-to-debug problems. Thats why we use Volatile
 so changes made to that variable are immediately visible to all threads

 synchronized(this) {
 Synchronized Block: You can use synchronized to create a synchronized block of code.
 This block ensures that only one thread can execute the code enclosed within the block at any given time.
 */