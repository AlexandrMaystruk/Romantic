package maystruks08.gmail.com.romantic

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.transaction (block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().block().commit()
}

fun Context.toast (text: String = "Some text") {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun DisplayMetrics.toDp (px: Float) = (px/(this.densityDpi/ DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toPx (dp: Float): Float = (dp*(this.densityDpi/ DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toDp (px: Int) = (px/(this.densityDpi/ DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toPx (dp: Int) = (dp*(this.densityDpi/ DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.spToPx (sp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this)

fun Context.getDisplayWidth () = this.resources.displayMetrics.widthPixels

fun Context.getDisplayHeight () = this.resources.displayMetrics.heightPixels