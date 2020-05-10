package com.github.ccmagic.db;

import com.github.ccmagic.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static void main(String[] args) throws Exception {
        Log.i("start........ ");
        if (args.length < 1) {
            Log.i("arguments: annotated classes");
            System.exit(0);
        }

        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                Log.i("NO DBTable annotations in class : " + className);
                continue;
            }
            String  tableName = dbTable.name();

            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName;
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations.length < 1) {
                    continue;
                }

                if (annotations[0] instanceof SQLInteger) {
                    SQLInteger sqlInteger = (SQLInteger) annotations[0];
                    if (sqlInteger.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sqlInteger.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sqlInteger.constraints()));
                }

                if (annotations[0] instanceof SQLString) {
                    SQLString sqlString = (SQLString) annotations[0];
                    if (sqlString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sqlString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sqlString.value() + ")" + getConstraints(sqlString.constraints()));
                }

            }

            StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "C");
            for (String columnDef : columnDefs) {
                createCommand.append("\n   ").append(columnDef).append(",");
            }
            String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
            Log.i("Table Creation SQL for " + className + "  is : \n" + tableCreate);
        }


        Log.i("exit........ ");
    }

    public static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }

        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }

        if (con.unique()) {
            constraints += "UNIQUE";
        }

        return constraints;
    }
}
