package tads.eaj.ufrn.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
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

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_luz, container, false)
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        text = binding.tvText
        luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val luz1 = event.values[0]
            text.text = "Sensor: ${luz1}"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, luz, SensorManager.SENSOR_DELAY_NORMAL)
    }

}