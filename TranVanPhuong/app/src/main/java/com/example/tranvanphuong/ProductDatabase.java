package com.example.tranvanphuong;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductEntity.class},version = 1)
public abstract class ProductDatabase  extends RoomDatabase {
 private static ProductDatabase productDatabase;
 public abstract ProductDao productDao();
 public static ProductDatabase getProductDatabase(Context context){
     if(productDatabase == null){
         productDatabase = Room.databaseBuilder(context,ProductDatabase.class,"News").allowMainThreadQueries().build();
     }
     return productDatabase;
 }

}
