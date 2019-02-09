package maystruks08.gmail.com.romantic

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.transaction (block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().block().commit()
}