package com.example.swapan.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by swapan on 2/13/17.
 */

@Database(name = TodoDatabase.NAME, version = TodoDatabase.VERSION)
public class TodoDatabase {

    public static final String NAME = "TodoDatabase";

    public static final int VERSION = 1;
}
