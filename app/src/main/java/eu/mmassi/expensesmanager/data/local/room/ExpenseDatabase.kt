package eu.mmassi.expensesmanager.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import eu.mmassi.expensesmanager.models.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

@Database(entities = [Expense::class], version = 1)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile
        private lateinit var instance: ExpenseDatabase

        fun getInstance(context: Context): ExpenseDatabase =
            if (::instance.isInitialized) instance else synchronized(ExpenseDatabase::class) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java, "expense_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
                    .also { instance = it }
            }

        private val roomCallback = object : RoomDatabase.Callback() {

            private val coroutineScope = CoroutineScope(Dispatchers.IO)

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                coroutineScope.launch {
                    val expenseDao = instance.expenseDao()
                    expenseDao.insert(Expense("title 1", "description 1", 1.0, ZonedDateTime.now()))
                    expenseDao.insert(Expense("title 2", "description 2", 2.0, ZonedDateTime.now()))
                    expenseDao.insert(Expense("title 3", "description 3", 3.0, ZonedDateTime.now()))
                    expenseDao.insert(Expense("title 4", "description 4", 4.0, ZonedDateTime.now()))
                }
            }
        }
    }
}
