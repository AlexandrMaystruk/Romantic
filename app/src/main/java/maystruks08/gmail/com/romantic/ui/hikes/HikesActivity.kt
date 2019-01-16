package maystruks08.gmail.com.romantic.ui.hikes

import android.os.Bundle
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.core.BaseNavigationActivity

class HikesActivity : BaseNavigationActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.operationComponent?.inject(this)
    }
}
