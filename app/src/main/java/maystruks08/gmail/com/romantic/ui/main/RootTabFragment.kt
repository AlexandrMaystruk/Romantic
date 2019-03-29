package maystruks08.gmail.com.romantic.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_root_tab.*
import maystruks08.gmail.com.romantic.R

class RootTabFragment : Fragment() {

   lateinit var fragmentAdapter: PagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_root_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentAdapter = PagerAdapter(childFragmentManager)
        rootTabs.setupWithViewPager(rootViewPager)
        rootViewPager.setPageTransformer(true, ZoomOutPageTransformer())
        rootViewPager.adapter = fragmentAdapter
    }

    companion object {
        fun getInstance(): RootTabFragment = RootTabFragment()
    }
}