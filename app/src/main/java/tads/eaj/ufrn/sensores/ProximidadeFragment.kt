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
import tads.eaj.ufrn.sensores.databinding.FragmentProximidadeBinding
import kotlin.math.absoluteValue

class ProximidadeFragment : Fragment(), SensorEventListener {

    lateinit var binding: FragmentProximidadeBinding
    private lateinit var sensorManager: SensorManager
    private var proximidade: Sensor? = null
    private lateinit var text: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proximidade, container, false)

        text = binding.tvText
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximidade = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            val proximidade1 = event.values[0]
            text.text = "Sensor: ${proximidade1}"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, proximidade, SensorManager.SENSOR_DELAY_NORMAL)
    }
}