package eu.mmassi.ikakebo.ui.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import eu.mmassi.ikakebo.R
import java.text.DateFormat
import java.util.Locale
import org.threeten.bp.ZonedDateTime

private var dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

@BindingAdapter("formattedTime")
fun setFormattedTime(textView: TextView, time: ZonedDateTime) {
    textView.text = dateFormatter.format(time.toInstant().toEpochMilli())
}

@BindingAdapter("formattedAmount")
fun setFormattedTime(textView: TextView, amount: Double) {
    textView.text = textView.context.getString(R.string.amount_euro_format, amount)
}
