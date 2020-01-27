package eu.mmassi.ikakebo.data.local

import androidx.lifecycle.LiveData
import eu.mmassi.ikakebo.models.Expense

interface LocalExpenseDataSource {

    suspend fun insert(expense: Expense)

    suspend fun update(expense: Expense)

    suspend fun delete(expense: Expense)

    suspend fun deleteExpenses()

    fun getAllExpenses(): LiveData<List<Expense>>

    fun getExpenseById(expenseId: Int): LiveData<Expense>
}
