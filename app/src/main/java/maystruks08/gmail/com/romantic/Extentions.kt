package maystruks08.gmail.com.romantic

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import maystruks08.gmail.com.domain.entity.Point
import maystruks08.gmail.com.romantic.ui.utils.GlideApp
import org.osmdroid.util.GeoPoint
import java.util.*

fun FragmentManager.transaction(block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().block().commit()
}

fun Context.toast(text: String = "Some text") {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun DisplayMetrics.toDp(px: Float) = (px / (this.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toPx(dp: Float): Float = (dp * (this.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toDp(px: Int) = (px / (this.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.toPx(dp: Int) = (dp * (this.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun DisplayMetrics.spToPx(sp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this)

fun Context.getDisplayWidth() = this.resources.displayMetrics.widthPixels

fun Context.getDisplayHeight() = this.resources.displayMetrics.heightPixels

fun Point.toGeoPoint() = this.let { GeoPoint(it.lat, it.lon) }

fun GeoPoint.toPoint(number: Int) = this.let { Point(UUID.randomUUID().toString(), number, it.latitude, it.longitude) }

fun lerp(a: Float, b: Float, t: Float): Float {
    return a + (b - a) * t
}

fun ConstraintLayout.loadFromUrl(url: String, @DrawableRes placeHolderRes: Int? = null) {
    var gl = GlideApp.with(this.context.applicationContext).load(url).fallbackDrawable
    //todo Implement this!!!
}

fun ImageView.loadFromUrl(url: String, @DrawableRes placeHolderRes: Int? = null) {
    var gl = GlideApp.with(this.context.applicationContext).load(url)
    if (placeHolderRes != null) {
        gl = gl.placeholder(placeHolderRes)
    }
    gl.into(this)
}

fun ImageView.loadFromDrawable(id: Int) =
    GlideApp.with(this.context.applicationContext).load(resources.getDrawable(id, resources.newTheme())).into(this)


fun ImageView.loadFromUrlWithKey(key: String, @DrawableRes placeHolderRes: Int? = null) {
    var gl = GlideApp.with(this.context.applicationContext).load(key)
    if (placeHolderRes != null) {
        gl = gl.placeholder(placeHolderRes)
    }
    gl.into(this)
}

fun Animation.withEndActionOnce(action: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            setAnimationListener(null)
            action()
        }

        override fun onAnimationStart(animation: Animation?) {
        }
    })
}

fun Animation.withStartActionOnce(action: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {

        }

        override fun onAnimationStart(animation: Animation?) {
            setAnimationListener(null)
            action()
        }
    })
}
