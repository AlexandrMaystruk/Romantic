//package maystruks08.gmail.com.core
//
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.drawerlayout.widget.DrawerLayout
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.LinearLayoutManager
//import maystruks08.gmail.com.romantic.App
//import ru.terrakok.cicerone.Navigator
//import ru.terrakok.cicerone.NavigatorHolder
//import ru.terrakok.cicerone.Router
//import ru.terrakok.cicerone.commands.BackTo
//import ru.terrakok.cicerone.commands.Command
//import ru.terrakok.cicerone.commands.Forward
//import javax.inject.Inject
//
//
//abstract class BaseNavigationActivity : AppCompatActivity() {
//
//    @Inject
//    lateinit var router: Router
//
//    @Inject
//    lateinit var navigatorHolder: NavigatorHolder
//
//
//    private var currentScene = ""
//    private var isOpen = false
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.drawer_layout)
//
//        App.operationComponent?.inject(this)
//
//        router.navigateTo(Screens.MENU_SCENE)
//    }
//
//
//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        navigatorHolder.setNavigator(navigator)
//    }
//
//    private val navigator = object : Navigator {
//        override fun applyCommands(commands: Array<out Command>?) {
//            commands?.forEach {
//                when (it) {
//                    is Forward -> onForwardCommand(it)
//                    is BackTo -> onBackCommand(it)
//                }
//            }
//        }
//
//        fun onForwardCommand(command: Forward) {
//            if (command.screenKey == Screens.CASH_PAYMENT_SCENE && currentScene != Screens.CASH_PAYMENT_SCENE) {
//                val amount = command.transitionData as Double
//                currentScene = Screens.CASH_PAYMENT_SCENE
//
//                supportFragmentManager.transaction {
//                    replace(R.id.mainContainer, PayManagerFragment.getInstance(amount, PayManagerFragment.METHOD_CASH))
//                    addToBackStack(Screens.PAYMENT_SCENE)
//                }
//            } else if (command.screenKey == Screens.CASHLESS_PAYMENT_SCENE && currentScene != Screens.CASHLESS_PAYMENT_SCENE) {
//                val amount = command.transitionData as Double
//                currentScene = Screens.CASHLESS_PAYMENT_SCENE
//                supportFragmentManager.transaction {
//                    replace(R.id.mainContainer, PayManagerFragment.getInstance(amount, PayManagerFragment.METHOD_EFT))
//                    addToBackStack(Screens.PAYMENT_SCENE)
//                }
//            } else if (command.screenKey == Screens.BILL_SCENE && currentScene != Screens.BILL_SCENE) {
//                currentScene = Screens.BILL_SCENE
//                supportFragmentManager.transaction {
//                    replace(R.id.mainContainer, BillFragment())
//                    addToBackStack(Screens.BILL_SCENE)
//                }
//            } else if (command.screenKey == Screens.CLOSE_DAY_SCENE && currentScene != Screens.CLOSE_DAY_SCENE) {
//                currentScene = Screens.CLOSE_DAY_SCENE
//                supportFragmentManager.transaction {
//                    replace(R.id.mainContainer, CloseDayFragment())
//                    addToBackStack(Screens.CLOSE_DAY_SCENE)
//                }
//            } else if (command.screenKey == Screens.MENU_SCENE && currentScene != Screens.MENU_SCENE) {
//                currentScene = Screens.MENU_SCENE
//                supportFragmentManager.transaction {
//                    replace(R.id.mainContainer, OrderFragment(), Screens.MENU_SCENE)
//                }
//            }
//        }
//
//        fun onBackCommand(command: BackTo) {
//            currentScene = Screens.MENU_SCENE
//            activateDrawerItem(DRAWER_MAIN)
//
//            onBackPressed()
//        }
//    }
//
//    companion object {
//
//        const val DRAWER_MAIN = 0
//
//        const val DRAWER_COSEDAY = 1
//
//        const val DRAWER_WARTUNG = 2
//
//        const val DRAWER_ADMINISTRATOR = 3
//
//    }
//
//}