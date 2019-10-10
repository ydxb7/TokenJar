package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentAddressDetailBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class AddressDetailFragment : Fragment(){

    private lateinit var binding: FragmentAddressDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressDetailBinding.inflate(inflater, container, false)



        binding.backBtn.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return binding.root
    }


}