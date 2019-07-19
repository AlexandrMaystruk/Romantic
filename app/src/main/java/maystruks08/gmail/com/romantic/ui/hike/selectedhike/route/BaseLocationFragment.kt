package maystruks08.gmail.com.romantic.ui.hike.selectedhike.route

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.location.Location


abstract class BaseLocationFragment : Fragment(), LocationListener {

    protected var mLocationManager: LocationManager? = null
    private var mInternetDialog: AlertDialog? = null
    private var mGPSDialog: AlertDialog? = null

    private val mNetworkDetectReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkInternetConnection()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLocationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
        try {
            mLocationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this)
        } catch (e: SecurityException) {
            Log.e("location", e.localizedMessage)
        }
        context?.registerReceiver(mNetworkDetectReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun checkInternetConnection() {
        val manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = manager.activeNetworkInfo

        if (ni != null && ni.state == NetworkInfo.State.CONNECTED) {

        } else {
            showNoInternetDialog()
        }
    }

    override fun onLocationChanged(location: Location) {}

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {
        if (provider == LocationManager.GPS_PROVIDER) {
            showGPSDisabledDialog()
        }
    }

    private fun showNoInternetDialog() {
        if (mInternetDialog != null && mInternetDialog!!.isShowing) {
            return
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Internet Disabled!")
        builder.setMessage("No active Internet connection found.")
        builder.setPositiveButton("Turn On") { dialog, which ->
            val gpsOptionsIntent = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)
            startActivityForResult(gpsOptionsIntent, WIFI_ENABLE_REQUEST)
        }.setNegativeButton("No, Just Exit") { dialog, which -> }
        mInternetDialog = builder.create()
        mInternetDialog!!.show()
    }

    private fun showGPSDisabledDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("GPS Disabled")
        builder.setMessage("Gps is disabled, in order to use the application properly you need to enable GPS of your device")
        builder.setPositiveButton("Enable GPS") { dialog, which ->
            startActivityForResult(
                Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                GPS_ENABLE_REQUEST
            )
        }
            .setNegativeButton("No, Just Exit") { dialog, which ->
                dialog.dismiss()
                dialog.cancel()

            }
        mGPSDialog = builder.create()
        mGPSDialog?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GPS_ENABLE_REQUEST) {
            if (mLocationManager == null) {
                mLocationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
            }

            if (!mLocationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showGPSDisabledDialog()
            }
        } else if (requestCode == WIFI_ENABLE_REQUEST) {

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        context?.unregisterReceiver(mNetworkDetectReceiver)
    }

    companion object {
        private const val GPS_ENABLE_REQUEST = 0x1001
        private const val WIFI_ENABLE_REQUEST = 0x1006
    }
}