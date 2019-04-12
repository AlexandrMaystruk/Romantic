package maystruks08.gmail.com.romantic.core.di.application.root.news

import dagger.Subcomponent
import maystruks08.gmail.com.romantic.ui.news.NewsFragment


@Subcomponent(modules = [NewsModule::class])
@NewsScope
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}