package maystruks08.gmail.com.romantic.ui.hikes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import maystruks08.gmail.com.romantic.App
import maystruks08.gmail.com.romantic.R

class HikeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hike_list_fragment)

        App.operationComponent?.inject(this)
    }
}
