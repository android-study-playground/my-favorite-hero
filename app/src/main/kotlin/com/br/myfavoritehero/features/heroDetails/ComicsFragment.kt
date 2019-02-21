package com.br.myfavoritehero.features.heroDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.myfavoritehero.R

class ComicsFragment : Fragment() {

    companion object {
        fun newInstance() = ComicsFragment()
    }

    private lateinit var viewModel: ComicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ComicsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
