package maystruks08.gmail.com.romantic.core.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import maystruks08.gmail.com.domain.entity.TypeHike
import maystruks08.gmail.com.romantic.ui.viewmodel.HikeViewModel
import ru.terrakok.cicerone.Screen

open class AppScreen : Screen() {

    open fun getFragment(): Fragment? {
        return null
    }

    fun getActivityIntent(context: Context): Intent? {
        return null
    }
}
