package au.sjowl.bottomsliderlayout

import android.graphics.Rect
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bottomsheet.*

class HomeFragment : Fragment() {

    val layoutId: Int get() = R.layout.fragment_home

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sheetContainer.setGone()

        bottomSheetToggleButton.setOnClickListener { sheetBehavior.toggleState() }
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            val cs = ConstraintSet()
            val rect = Rect()

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                bottomSheetToggleButton.rotation = slideOffset * 180

                val paddingTop = ((1 - slideOffset) * (bottomContainer.height / 2)).toInt()

                sheetContainer.getGlobalVisibleRect(rect)
                cs.clone(sheetContainer)
                val anchor = ConstraintSet.TOP
                sheetContainer.setPadding(0, paddingTop, 0, 0)
                cs.setMargin(bottomContainer.id, anchor, rect.height() - bottomContainer.height - paddingTop)
                cs.setMargin(bottomContainerTop.id, anchor, rect.height() - bottomContainer.height - bottomContainerTop.height - paddingTop)
                cs.applyTo(sheetContainer)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> hideSheet()
                    else -> showSheet()
                }
            }
        })
    }

    fun hideSheet() {
        sheetContainer.setGone()
    }

    fun showSheet() {
        sheetContainer.setVisible()
    }

    companion object {
        @JvmStatic fun newInstance() = HomeFragment()
    }
}