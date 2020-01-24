package eu.mmassi.expensesmanager.ui.expenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.models.Expense
import kotlinx.android.synthetic.main.expense_item.view.amount
import kotlinx.android.synthetic.main.expense_item.view.description
import kotlinx.android.synthetic.main.expense_item.view.time
import kotlinx.android.synthetic.main.expense_item.view.title
import org.threeten.bp.format.DateTimeFormatter

class ExpensesAdapter : ListAdapter<Expense, ExpensesAdapter.ExpenseHolder>(expenseDiffCallback) {

    private var listener: OnExpenseClickListener? = null
    private var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExpenseHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false))

    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) = getItem(position).run {
        holder.amount.text = holder.itemView.context.getString(R.string.amount_euro_format, amount)
        holder.title.text = title
        holder.description.text = description
        holder.time.text = formatter.format(time)
    }

    fun getExpenseAt(position: Int): Expense = getItem(position)

    inner class ExpenseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?.let { listener?.onExpenseClick(getItem(it)) }
            }
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
