package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

public final class TodoContract {

    // TODO 1. 定义创建数据库以及升级的操作
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoNote.TABLE_NAME + '(' +
                    TodoNote._ID + " INTEGER PRIMARY KEY," +
                    TodoNote.DATE + " INTEGER, "  +    // 时间
                    TodoNote.STATE + " INTEGER, " +    // 状态
                    TodoNote.CONTENT + " TEXT, "  +    // 内容
                    TodoNote.PRIORITY + " INTEGER)";   // 优先级

    public static final String SQL_ADD_PRIORITY_COLUMN =
            "ALTER TABLE " + TodoNote.TABLE_NAME + " ADD " + TodoNote.PRIORITY + " INTEGER";

    private TodoContract() {

    }

    public static class TodoNote implements BaseColumns {
        // TODO 2.此处定义表名以及列明
        public static final String TABLE_NAME = "note";
        public static final String STATE = "state";
        public static final String DATE = "date";
        public static final String CONTENT = "content";
        public static final String PRIORITY = "priority";
    }

}
