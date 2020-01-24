package eu.mmassi.expensesmanager.ui.expenses

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.models.Expense
import kotlinx.android.synthetic.main.fragment_expenses.expenses

class ExpensesFragment : Fragment(R.layout.fragment_expenses) {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expenses.layoutManager = LinearLayoutManager(activity)
        expenses.setHasFixedSize(true)

        val adapter = ExpensesAdapter()

        expenses.adapter = adapter

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.getExpenses().observe(this) {
            adapter.submitList(it)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                expenseViewModel.delete(adapter.getExpenseAt(viewHolder.adapterPosition))
            }
        }
        ).attachToRecyclerView(expenses)

        adapter.setOnExpenseClickListener(object : ExpensesAdapter.OnExpenseClickListener {
            override fun onExpenseClick(expense: Expense) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_expenses -> {
                expenseViewModel.deleteExpenses()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
