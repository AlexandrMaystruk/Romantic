package maystruks08.gmail.com.romantic.core.navigation;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import maystruks08.gmail.com.domain.entity.Hike;
import ru.terrakok.cicerone.Screen;

import java.util.List;

public abstract class AppScreen extends Screen {

    public Fragment getFragment() {
        return null;
    }

    public Fragment getFragment(List<Hike> hike) {
        return null;
    }

    public Intent getActivityIntent(Context context) {
        return null;
    }
}
