package eu.mmassi.ikakebo.data.local.room

import android.app.Application
import eu.mmassi.ikakebo.data.local.LocalExpenseDataSource
import eu.mmassi.ikakebo.models.Expense

class RoomLocalExpenseDataSource(application: Application) : LocalExpenseDataSource {

    private val expenseDao = ExpenseDatabase.getInstance(application).expenseDao()
    private val allExpenses = expenseDao.getExpenses()

    override suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    override suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    override suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    override suspend fun deleteExpenses() {
        expenseDao.deleteExpenses()
    }

    override fun getAllExpenses() = allExpenses

    override fun getExpenseById(expenseId: Int) = expenseDao.getExpenseById(expenseId)
}
