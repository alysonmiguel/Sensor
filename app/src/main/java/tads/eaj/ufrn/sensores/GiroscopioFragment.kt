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
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.sensores.databinding.FragmentGiroscopioBinding
import tads.eaj.ufrn.sensores.databinding.FragmentProximidadeBinding

class GiroscopioFragment : Fragment() , SensorEventListener {

    lateinit var binding: FragmentGiroscopioBinding
    private lateinit var sensorManager: SensorManager
    private var giroscopio: Sensor? = null
    private lateinit var text: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_giroscopio, container, false)

        text = binding.tvText
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val g = event.values[0]
            text.text = "Sensor: ${g}"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL)
    }
}