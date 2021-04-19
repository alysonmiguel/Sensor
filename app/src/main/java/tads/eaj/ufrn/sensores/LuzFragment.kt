package tads.eaj.ufrn.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import tads.eaj.ufrn.sensores.databinding.FragmentLuzBinding

class LuzFragment : Fragment(), SensorEventListener {

    lateinit var binding: FragmentLuzBinding
    private lateinit var sensorManager: SensorManager
    private var luz: Sensor? = null
    private lateinit var text: TextView
    private lateinit var pb: CircularProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_luz, container, false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        text = binding.tvText
        pb = binding.circularProgressBar

        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val luz1 = event.values[0]

            text.text = "Sensor: ${luz1}"
            pb.setProgressWithAnimation(luz1)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }


    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this, luz, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


//    private fun luz(luz: Float): String {
//
//        return when (luz.toInt()) {
//            0 -> "Pitch black"
//            in 1..10 -> "Dark"
//            in 11..50 -> "Grey"
//            in 51..5000 -> "Normal"
//            in 5001..25000 -> "Incredibly bright"
//            else -> "This light will blind you"
//        }
//    }

}