package eu.mmassi.ikakebo.ui.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.mmassi.ikakebo.databinding.ItemExpenseBinding
import eu.mmassi.ikakebo.models.Expense
import kotlinx.android.synthetic.main.item_expense.view.amount
import kotlinx.android.synthetic.main.item_expense.view.description
import kotlinx.android.synthetic.main.item_expense.view.time
import kotlinx.android.synthetic.main.item_expense.view.title

class ExpensesAdapter : ListAdapter<Expense, ExpensesAdapter.ExpenseHolder>(expenseDiffCallback) {

    private var listener: OnExpenseClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExpenseBinding.inflate(inflater, parent, false)
        return ExpenseHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) = getItem(position).run {
        holder.bind(this)
    }

    fun getExpenseAt(position: Int): Expense = getItem(position)

    inner class ExpenseHolder(private val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?.let { listener?.onExpenseClick(getItem(it)) }
            }
        }

        fun bind(expense: Expense) {
            binding.expense = expense
            binding.executePendingBindings()
        }

        val amount: TextView = itemView.amount
        val title: TextView = itemView.title
        val description: TextView = itemView.description
        val time: TextView = itemView.time
    }

    fun setOnExpenseClickListener(listener: OnExpenseClickListener) {
        this.listener = listener
    }

    interface OnExpenseClickListener {
        fun onExpenseClick(expense: Expense)
    }

    companion object {

        private val expenseDiffCallback = object : DiffUtil.ItemCallback<Expense>() {

            override fun areItemsTheSame(old: Expense, new: Expense) = old.id == new.id

            override fun areContentsTheSame(old: Expense, new: Expense) = old == new
        }
    }
}
