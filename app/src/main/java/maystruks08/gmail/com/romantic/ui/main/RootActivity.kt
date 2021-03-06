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
import androidx.core.content.ContextCompat
import maystruks08.gmail.com.data.preferences.AuthPreferences
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.ui.launcher.SplashActivity

class RootActivity : AppCompatActivity(), View.OnClickListener, RootContract.View, UploadListener, ConfigToolbar {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var presenter: RootContract.Presenter

    @Inject
    lateinit var pref: AuthPreferences

    private var onBackHandler: Runnable? = null

    private var lastBackPressTime = 0L

    private var optionsMenu: Menu? = null

    private lateinit var menuClickListener: (Int) -> Unit

    private val bottomButtonMap: MutableMap<CircleImageView, TextView> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setToolbar()

        App.rootComponent?.inject(this)
        bottomNavigationClickHandler()
        presenter.bindView(this)
        router.newRootScreen(Screens.RootTabScreen())
        presenter.syncData()
    }


    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbarIcon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            bottom_news -> {
                router.newRootScreen(Screens.NewsScreen())
            }
            bottom_message -> {
                router.newRootScreen(Screens.MessageScreen())
            }
            bottom_main -> {
                router.newRootScreen(Screens.RootTabScreen())
            }
            bottom_events -> {
                router.newRootScreen(Screens.EventScreen())
            }
            bottom_my_hikes -> {
                router.newRootScreen(Screens.MyHikesScreen())
            }
            toolbarIcon -> {
                router.navigateTo(Screens.ProfileSettingsScreen(pref.getCurrentUser()!!))
            }
        }

        bottomButtonMap.keys.forEach {
            if (v?.id == it.id) {
                it.borderWidth = 2
                bottomButtonMap[it]?.setTextColor(Color.WHITE)

            } else {
                it.borderWidth = 0
                bottomButtonMap[it]?.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textWhiteUnselected
                    )
                )
            }
        }
    }


    override fun uploadHikes() {
        presenter.onUploadHikes()
    }

    override fun updateHikes() {
        presenter.onUpdateHikes()
    }

    override fun updateParticipants() {
        presenter.onUpdateParticipants()
    }

    override fun uploadParticipants() {
        presenter.onUploadParticipants()
    }

    override fun uploadRoutes() {
        presenter.uploadRoutes()
    }

    override fun updateRoutes() {
        presenter.updateRoutes()
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

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }


    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        optionsMenu = menu
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sing_out -> {
                presenter.logout()
                true
            }
            else -> {
                menuClickListener(item.itemId)
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun startLauncherActivity() {
        this.startActivity(Intent(this, SplashActivity::class.java))
        finish()
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
        supportActionBar?.show()
    }

    override fun disableToolbar() {
        supportActionBar?.hide()
    }

    override fun setBackgroundColor(color: Int) {
        ContextCompat.getColor(this, color).let{
            toolbar.setBackgroundColor(it)
        }
    }

    override fun enableLogoIcon() {
        toolbarIcon.visibility = View.VISIBLE
    }

    override fun disableLogoIcon() {
        toolbarIcon.visibility = View.GONE
    }

    override fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
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

    override fun enableBottomBar() {
        rootBottomNavigationBar.visibility = View.VISIBLE
    }

    override fun disableBottomBar() {
        rootBottomNavigationBar.visibility = View.GONE
    }

    override fun showOptionMenu(showMenu: Boolean) {
        if (optionsMenu == null) return
//        this.optionsMenu?.setGroupVisible(R.id.main_menu_group, showMenu)
    }

    override fun setOptionMenuClickListener(clickListener: (Int) -> Unit) {
        menuClickListener = clickListener
    }

    override fun setOptionMenu(menuRes: Int) {
        optionsMenu?.let {
            it.clear()
            menuInflater.inflate(menuRes, it)
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(t: Throwable) {
    }
}