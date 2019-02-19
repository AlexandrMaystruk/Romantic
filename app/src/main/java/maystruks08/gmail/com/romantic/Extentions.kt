package maystruks08.gmail.com.romantic

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.transaction (block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().block().commit()
}

fun Context.toast (text: String = "Some text") {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}