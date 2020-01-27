package eu.mmassi.ikakebo.ui.expenses

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.mmassi.ikakebo.R
import eu.mmassi.ikakebo.models.Expense
import kotlinx.android.synthetic.main.fragment_expenses.add_expense
import kotlinx.android.synthetic.main.fragment_expenses.expenses

class ExpensesFragment : Fragment(R.layout.fragment_expenses) {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        add_expense.setOnClickListener {
            findNavController().navigate(
                ExpensesFragmentDirections.actionExpensesFragmentToAddEditExpenseFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.expenses_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.delete_all_expenses -> {
                expenseViewModel.deleteExpenses()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setupRecyclerView() {
        expenses.layoutManager = LinearLayoutManager(activity)
        expenses.setHasFixedSize(true)

        val adapter = ExpensesAdapter()

        expenses.adapter = adapter

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.getExpenses().observe(this) {
            adapter.submitList(it)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, LEFT.or(RIGHT)) {

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
        }).attachToRecyclerView(expenses)

        adapter.setOnExpenseClickListener(object : ExpensesAdapter.OnExpenseClickListener {
            override fun onExpenseClick(expense: Expense) {
                findNavController().navigate(
                    ExpensesFragmentDirections
                    .actionExpensesFragmentToAddEditExpenseFragment(expense.id))
            }
        })
    }
}
