package com.example.tranvanphuong;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class ProductEntity {
@PrimaryKey(autoGenerate = true)
    public int Id;
@ColumnInfo(name = "name")
    public String Name;
@ColumnInfo(name = "quantity")
    public Integer Quantity;

}
