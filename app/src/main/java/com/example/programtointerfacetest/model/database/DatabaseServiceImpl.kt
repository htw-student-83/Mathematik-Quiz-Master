package com.example.programtointerfacetest.model.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User

internal class DatabaseServiceImpl(private val context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DatabaseService {

    companion object {
        private const val DATABASE_NAME = "Mathematics-Quiz-Master"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "Users"

        private const val COLUMN_USERID = "ui"
        private const val COLUMN_FIRSTNAME = "fn"
        private const val COLUMN_LASTNAME = "ln"
        private const val COLUMN_PASSWORD = "pw"
        private const val COLUMN_POINTS = "pt"
        private const val COLUMN_LOGGED = "state"
        private const val COLUMN_ACTIVE_SINCE = "date"
    }

    //hier wird wohl die Tabelle mit den Spalten erzeugt
    override fun onCreate(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_USERID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRSTNAME TEXT,
                $COLUMN_LASTNAME TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_POINTS INTEGER,
                $COLUMN_LOGGED BOOLEAN,
                $COLUMN_ACTIVE_SINCE DATE
            );
        """.trimIndent()
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun saveNewUser(firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): String {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME, firstname)
            put(COLUMN_LASTNAME, lastname)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_POINTS, points)
            put(COLUMN_LOGGED, logged)
            put(COLUMN_ACTIVE_SINCE, activeSince)
        }

        val result = db.insert(TABLE_NAME, null, values)
        return if(result != -1L) result.toString() else "failed"
    }

    override fun saveNewUser2(
        firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME, firstname)
            put(COLUMN_LASTNAME, lastname)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_POINTS, points)
            put(COLUMN_LOGGED, logged)
            put(COLUMN_ACTIVE_SINCE, activeSince)
        }
        val newRowId = db.insert(TABLE_NAME, null, values)
        return newRowId
    }

    override fun checkUserPW(password: String): String {
        val db = writableDatabase

        val select = "SELECT $COLUMN_USERID FROM $TABLE_NAME WHERE $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(select, arrayOf(password))

        return if(cursor.moveToFirst()){
            val userId = cursor.getLong(0)
            cursor.close()

            // 2. logged auf 1 setzen für den gefundenen User
            val values = ContentValues().apply {
                put(COLUMN_LOGGED, 1)
            }
            val rowsAffected = db.update(
                TABLE_NAME,
                values,
                "$COLUMN_USERID = ?",
                arrayOf(userId.toString())
            )
            //debugLogAllUsers()
            //clearAllUsers()
            val sharedPrefUSERID = context.getSharedPreferences("USERID", Context.MODE_PRIVATE)
            sharedPrefUSERID.edit().putLong("UID", userId).apply()

            if(rowsAffected > 0) "success" else "Password is unknown"
        }else{
            cursor.close()
            "Password is unknown"
        }

    }

    override fun saveGamePoints(points: Int): Boolean {
        //TODO only the content of points must be changed.
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_POINTS, points)
        }

        val result = db.insert(TABLE_NAME, null, values)
        return result == -1L
    }

    override fun reloadUserData(): User? {
        val db = readableDatabase
        val sql = "SELECT $COLUMN_FIRSTNAME, $COLUMN_LASTNAME, $COLUMN_POINTS, " +
                "$COLUMN_ACTIVE_SINCE FROM $TABLE_NAME WHERE $COLUMN_LOGGED = true"

        val cursor = db.rawQuery(sql, null)

        val userData = if (cursor.moveToFirst()) {
            val firstname = cursor.getString(0)
            val lastname = cursor.getString(1)
            val points = cursor.getInt(2)
            val activeSince = cursor.getString(3) // Oder .getLong(3), je nach Format

            User (
                firstname = "$firstname",
                lastname = "$lastname",
                highscore = "$points",
                activeSince = "$activeSince"
            )
        } else {
            null
        }
        cursor.close()
        return userData
    }

    override fun deleteAccount(userID: Long): Boolean {
        val db = writableDatabase
        val clausen = "$COLUMN_USERID = ?"
        val whereArgs = arrayOf(userID.toString())

        val deletedRows = db.delete(TABLE_NAME, clausen, whereArgs)
        db.close() // DB schließen nach Operation
        return deletedRows > 0
    }

    override fun logout(userID: Long): Boolean {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_LOGGED, false)
        }
        val rowsAffected = db.update(
            TABLE_NAME,
            values,
            "$COLUMN_USERID = ?",
            arrayOf(userID.toString())
        )
        debugLogAllUsers()
        return rowsAffected > 0
    }

    override fun reloadLoggedUser(): String {
        val db = readableDatabase

        val sql = "SELECT $COLUMN_FIRSTNAME, $COLUMN_LASTNAME " +
                "FROM $TABLE_NAME WHERE $COLUMN_LOGGED = 1"

        val cursor = db.rawQuery(sql, null)

        val result = if (cursor.moveToFirst()) {
            val firstName = cursor.getString(0)
            val lastName = cursor.getString(1) // Oder .getLong(3), je nach Format
            "$firstName  $lastName"
        } else {
            "Kein eingeloggter Benutzer gefunden"
        }
        cursor.close()
        return result
    }

    private fun debugLogAllUsers() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_USERID, $COLUMN_FIRSTNAME, $COLUMN_LASTNAME, $COLUMN_LOGGED FROM $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(0)
            val first = cursor.getString(1)
            val last = cursor.getString(2)
            val logged = cursor.getInt(3)
            Log.d("DEBUG_USER", "ID: $id, Name: $first $last, Logged: $logged")
        }
        cursor.close()
    }

    private fun clearAllUsers() {
        val db = writableDatabase
        val rowsDeleted = db.delete(TABLE_NAME, null, null)
        Log.d("Database", "$rowsDeleted Benutzer gelöscht.")
    }

    override fun loadUserDataForTopFive(): List<TopFiveUser?> {
        val db = readableDatabase

        val sql = "SELECT $COLUMN_FIRSTNAME, $COLUMN_LASTNAME, $COLUMN_POINTS " +
                "FROM $TABLE_NAME ORDER BY $COLUMN_POINTS DESC LIMIT 5 "

        val cursor = db.rawQuery(sql, null)

        val topFiveUsers = mutableListOf<TopFiveUser>()

        if (cursor.moveToFirst()) {
            do{
                val firstname = cursor.getString(0)
                val lastname = cursor.getString(1) // Oder .getLong(3), je nach Format
                val points = cursor.getString(2)
                topFiveUsers.add(TopFiveUser(firstname, lastname, points))
                Log.d("Inhalt der Liste",  "$firstname $lastname $points")
            }while (cursor.moveToNext())
        } else {
            "Kein eingeloggter Benutzer gefunden"
        }
        cursor.close()
        return topFiveUsers
    }
}