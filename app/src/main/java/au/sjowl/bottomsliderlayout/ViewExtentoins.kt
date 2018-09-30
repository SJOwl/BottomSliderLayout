package au.sjowl.bottomsliderlayout

import android.support.design.widget.BottomSheetBehavior
import android.view.View

fun <T : View> BottomSheetBehavior<T>.toggleState() {
    state = if (state != BottomSheetBehavior.STATE_EXPANDED) {
        BottomSheetBehavior.STATE_EXPANDED
    } else {
        BottomSheetBehavior.STATE_COLLAPSED
    }
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}