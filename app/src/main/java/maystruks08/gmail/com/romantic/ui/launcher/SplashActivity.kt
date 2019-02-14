package maystruks08.gmail.com.romantic.ui.launcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.PRESS_TWICE_INTERVAL
import maystruks08.gmail.com.romantic.R
import maystruks08.gmail.com.romantic.core.navigation.AppNavigator
import maystruks08.gmail.com.romantic.ui.main.RootActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private var onBackHandler: Runnable? = null

    private var lastBackPressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        App.launcherComponent?.inject(this)

        presenter.bindView(this)
        presenter.isAuthenticated()
    }

    private val navigator: Navigator = object : AppNavigator(this, supportFragmentManager, R.id.splashContainer) {

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
            onBackHandler != null -> onBackHandler?.run()
            supportFragmentManager.backStackEntryCount > 0 -> presenter.onBackPressed()
            lastBackPressTime < System.currentTimeMillis() - PRESS_TWICE_INTERVAL -> {
                Toast.makeText(this, R.string.toast_exit_app_warning_text, Toast.LENGTH_SHORT).show()
                lastBackPressTime = System.currentTimeMillis()
            }
            else -> finish()
        }
    }

    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun startRootActivity() {
        startActivity(Intent(this, RootActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.end()
        App.clearLauncherComponent()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showError(t: Throwable) {}

}