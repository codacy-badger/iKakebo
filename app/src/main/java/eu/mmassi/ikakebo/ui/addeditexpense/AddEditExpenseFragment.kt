package eu.mmassi.ikakebo.ui.addeditexpense

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import eu.mmassi.ikakebo.R

class AddEditExpenseFragment : Fragment(R.layout.add_edit_expense_fragment) {

    private lateinit var viewModel: AddEditExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddEditExpenseViewModel::class.java)
        val args: AddEditExpenseFragmentArgs by navArgs()
        val expenseId = args.expenseId

        (activity as? AppCompatActivity)?.supportActionBar?.title =
            getString(if (expenseId >= 0) R.string.edit_expense else R.string.add_expense)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.save_expense -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
