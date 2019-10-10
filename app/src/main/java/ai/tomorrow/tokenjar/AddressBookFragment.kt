package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentAddressBookBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class AddressBookFragment : Fragment(){

    private lateinit var binding: FragmentAddressBookBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddressBookBinding.inflate(inflater, container, false)
        return binding.root
    }


}