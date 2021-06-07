package com.example.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.models.Result;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomDao {
@NotNull
    @Insert(onConflict = REPLACE)
    abstract void insert(List<UserMainData> mainData);

    @Delete
    abstract void delete(UserMainData mainData);
    @Delete
    abstract void deleteAll(List<UserMainData> mainData);

    @Query("SELECT * FROM table_name")
    abstract List<UserMainData> getAllData();

}
