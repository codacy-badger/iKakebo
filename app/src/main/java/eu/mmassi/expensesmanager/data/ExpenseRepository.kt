package eu.mmassi.expensesmanager.data

import android.app.Application
import androidx.lifecycle.LiveData
import eu.mmassi.expensesmanager.data.local.LocalExpenseDataSource
import eu.mmassi.expensesmanager.data.local.room.RoomLocalExpenseDataSource
import eu.mmassi.expensesmanager.models.Expense
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpenseRepository private constructor(application: Application) {

    private val localExpenseDataSource: LocalExpenseDataSource =
        RoomLocalExpenseDataSource(application)
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    companion object {

        @Volatile
        private lateinit var instance: ExpenseRepository

        fun getInstance(application: Application): ExpenseRepository =
            if (::instance.isInitialized) instance else synchronized(this) {
                ExpenseRepository(application).also { instance = it }
            }
    }

    fun observeExpenses(): LiveData<List<Expense>> {
        return localExpenseDataSource.getAllExpenses()
    }

    fun observeExpense(expenseId: Int): LiveData<Expense> {
        return localExpenseDataSource.getExpenseById(expenseId)
    }

    suspend fun saveExpense(expense: Expense) {
        coroutineScope {
            launch { localExpenseDataSource.insert(expense) }
        }
    }

    suspend fun deleteExpenses() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { localExpenseDataSource.deleteExpenses() }
            }
        }
    }

    suspend fun deleteExpense(expense: Expense) {
        coroutineScope {
            launch { localExpenseDataSource.delete(expense) }
        }
    }
}
