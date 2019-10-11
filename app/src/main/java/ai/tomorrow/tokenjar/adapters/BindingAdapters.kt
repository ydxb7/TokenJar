package ai.tomorrow.tokenjar.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}