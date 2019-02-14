package maystruks08.gmail.com.romantic.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.PRESS_TWICE_INTERVAL
import maystruks08.gmail.com.romantic.core.navigation.AppNavigator
import maystruks08.gmail.com.romantic.core.navigation.Screens
import maystruks08.gmail.com.romantic.ui.ConfigToolbar
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject
import android.view.MenuItem
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.launcher.SplashActivity

class RootActivity : AppCompatActivity(), View.OnClickListener, LogOutContract.View, ConfigToolbar {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var logoutPresenter: LogOutContract.Presenter

    private var onBackHandler: Runnable? = null

    private var lastBackPressTime = 0L

    private var menu: Menu? = null

    private val bottomButtonMap: MutableMap<CircleImageView, TextView> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        App.appComponent.inject(this)
        logoutPresenter.bindView(this)

        router.newRootScreen(Screens.RootTabScreen)

        bottomNavigationClickHandler()
        setToolbar()
    }


    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onClick(v: View?) {
        when (v) {
            bottom_news -> {
                router.newRootScreen(Screens.NewsScreen)
            }
            bottom_message -> {
                router.newRootScreen(Screens.MessageScreen)
            }
            bottom_main -> {
                router.newRootScreen(Screens.RootTabScreen)
            }
            bottom_events -> {
                router.newRootScreen(Screens.EventScreen)
            }
            bottom_my_hikes -> {
                router.newRootScreen(Screens.MyHikeScreen)
            }
        }

        bottomButtonMap.keys.forEach {
            if (v?.id == it.id) {
                it.borderWidth = 2
                bottomButtonMap[it]?.setTextColor(Color.WHITE)

            } else {
                it.borderWidth = 0
                bottomButtonMap[it]?.setTextColor(resources.getColor(R.color.text_white_unselected))
            }
        }
    }

    private fun bottomNavigationClickHandler() {
        bottomButtonMap[bottom_news] = tvBottomNews
        bottomButtonMap[bottom_message] = tvBottomMessage
        bottomButtonMap[bottom_main] = tvBottomMain
        bottomButtonMap[bottom_events] = tvBottomEvents
        bottomButtonMap[bottom_my_hikes] = tvBottomMyHikes
        bottomButtonMap.keys.forEach {
            it.setOnClickListener(this)
        }
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sing_out -> {
                logoutPresenter.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun startLauncherActivity() {
        this.startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
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

    override fun enableToolbar() {
        actionBar?.show()
    }

    override fun disableToolbar() {
        actionBar?.hide()
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    override fun disableOverlay() {
        collapseView.visibility = View.VISIBLE
    }

    override fun enableOverlay() {
        collapseView.visibility = View.GONE
    }

    override fun setBackground(icon: Int) {
        toolbar.background = getDrawable(icon)
    }

    override fun setNavigationIcon(@DrawableRes icon: Int) {
        toolbar.setNavigationIcon(icon)
        toolbar.setNavigationOnClickListener {
            navigateBack()
        }
    }

    override fun removeNavigationIcon() {
        toolbar.navigationIcon = null
    }

    override fun showOptionMenu(showMenu: Boolean) {
        if (menu == null) return
//        this.menu?.setGroupVisible(R.id.main_menu_group, showMenu)
    }

}