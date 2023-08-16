package edu.dixietech.pgunnell.uselessfacts.utils

import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import edu.dixietech.pgunnell.uselessfacts.R

@BindingAdapter("isFavoriteIcon")
fun MaterialButton.isFavoriteIcon(favorite: Boolean?) {
    favorite?.let {
        this.icon = AppCompatResources.getDrawable(context, if (it) R.drawable.heart_filled else R.drawable.heart_outline)
    }
}