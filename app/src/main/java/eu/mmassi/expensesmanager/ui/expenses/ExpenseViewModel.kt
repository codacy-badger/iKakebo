package eu.mmassi.expensesmanager.ui.expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import eu.mmassi.expensesmanager.data.ExpenseRepository
import eu.mmassi.expensesmanager.models.Expense
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ExpenseRepository = ExpenseRepository.getInstance(application)
    private var expenses: LiveData<List<Expense>> = repository.observeExpenses()

    fun save(expense: Expense) =
        viewModelScope.launch {
            repository.saveExpense(expense)
        }

    fun delete(expense: Expense) =
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }

    fun deleteExpenses() =
        viewModelScope.launch {
            repository.deleteExpenses()
        }

    fun getExpenses() = expenses
}
