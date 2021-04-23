package tads.eaj.ufrn.sensores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import tads.eaj.ufrn.sensores.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.buttonProximidade.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_proximidadeFragment)
        }
        binding.buttonAcelerometro.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_acelerometroFragment)
        }
        binding.buttonLuz.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_Luz)
        }
        binding.buttonGiroscopio.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_giroscopioFragment)
        }


        return binding.root
    }
}