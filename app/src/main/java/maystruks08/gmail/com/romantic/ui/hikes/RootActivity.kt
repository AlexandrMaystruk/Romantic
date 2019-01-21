package maystruks08.gmail.com.romantic.ui.hikes

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.PRESS_TWICE_INTERVAL
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.core.navigation.AppNavigator
import maystruks08.gmail.com.romantic.core.navigation.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class RootActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var onBackHandler: Runnable? = null

    private var lastBackPressTime = 0L

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        App.appComponent.inject(this)

        router.newRootScreen(Screens.HikeListScreen)

    }

    private val navigator: Navigator = object : AppNavigator(this, supportFragmentManager, R.id.rootContainer) {

        override fun setupFragmentTransaction(
            command: Command?,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction
        ) {
            fragmentTransaction.setReorderingAllowed(true)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }


    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onBackPressed() {
        this.hideSoftKeyboard()
        this.navigateBack()
    }

    private fun navigateBack() {
        when {
            onBackHandler != null -> onBackHandler!!.run()
            supportFragmentManager.backStackEntryCount > 0 -> router.exit()
            lastBackPressTime < System.currentTimeMillis() - PRESS_TWICE_INTERVAL -> {
                Toast.makeText(this, R.string.toast_exit_app_warning_text, Toast.LENGTH_SHORT).show()
                lastBackPressTime = System.currentTimeMillis()
            }
            else -> router.exit()
        }
    }


    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

//    fun setBackground(icon: Int) {
//        mainBackground.background = getDrawable(icon)
//    }
//
//
//    fun setNavigationIcon(@DrawableRes icon: Int) {
//        main_toolbar.setNavigationIcon(icon)
//        main_toolbar.setNavigationOnClickListener {
//            navigateBack()
//        }
//    }
//
//    fun removeNavigationIcon() {
//        main_toolbar.navigationIcon = null
//    }
//
//    fun showOptionMenu(showMenu: Boolean) {
//        if (menu == null) return
//        this.menu?.setGroupVisible(R.id.main_menu_group, showMenu)
//    }
//
//
//    fun configToolbarOfflineMessage(isOffline: Boolean) {
//        if (isOffline) {
//            main_toolbar.title = null
//            tvTitleToolbar.visibility = View.VISIBLE
//            progressToolbar.visibility = View.VISIBLE
//
//        } else {
//            main_toolbar.title = getString(R.string.app_name)
//            tvTitleToolbar.visibility = View.GONE
//            progressToolbar.visibility = View.GONE
//        }
//    }
//
//    fun configOverlay(overlay: Boolean) {
//        if (overlay) {
//            viewFrameController.visibility = View.GONE
//        } else viewFrameController.visibility = View.VISIBLE
//
//    }
//
//    fun setToolbarTitle(title: String) {
//        main_toolbar.title = title
//    }
//    private fun setupBackgroundImage() {
//
//        val idImageBackground = getSharedPreferences("BACKGROUND_IMAGE", Context.MODE_PRIVATE)
//            ?.getString("BACKGROUND_IMAGE", R.drawable.default_back.toString())
//
//        if (idImageBackground != null) {
//            setBackground(idImageBackground.toInt())
//        }
//    }

}