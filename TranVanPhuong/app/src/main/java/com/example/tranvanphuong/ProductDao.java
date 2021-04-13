package com.example.tranvanphuong;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDao {
@Insert(onConflict = REPLACE)
    void insertProduct(ProductEntity product);
@Update
    void updateProduct(ProductEntity product);



}
