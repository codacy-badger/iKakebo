package eu.mmassi.expensesmanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "expenses")
data class Expense(
    var title: String,
    var description: String,
    var amount: Double,
    var time: ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
