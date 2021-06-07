package com.example.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Add
@Database(entities = {UserMainData.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private  static RoomDB roomDB;

    private static String DATABASE_NAME="database";
    public  synchronized static RoomDB getInstance(Context context){
        if (roomDB==null){
            roomDB=Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDB;
    }


    //Dao
    public abstract RoomDao roomDao();

}
