package ai.tomorrow.tokenjar

import ai.tomorrow.tokenjar.databinding.FragmentManageBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class ManageFragment : Fragment(){

    private lateinit var binding: FragmentManageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(inflater, container, false)

        binding.backBtn.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return binding.root
    }

}