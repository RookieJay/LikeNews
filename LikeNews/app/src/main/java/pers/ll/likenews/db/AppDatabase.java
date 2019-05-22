package pers.ll.likenews.db;

import com.raizlabs.android.dbflow.annotation.Database;

@SuppressWarnings({ "WeakerAccess", "unused" })
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "likeNews";

    public static final int VERSION = 1;

    public static final String ENCRYPT_KEY = "my_db";



}