package com.br.myfavoritehero.features.listComics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.myfavoritehero.data.interfaces.ComicEventListener
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.databinding.ComicsFragmentBinding
import com.br.myfavoritehero.features.listComics.adapter.ComicAdapter
import com.br.myfavoritehero.features.listComics.viewModel.ComicsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ComicsFragment : Fragment(), ComicEventListener {

    companion object {
        private const val HERO_ID = "HERO_ID"

        fun newInstance(heroId: Int): ComicsFragment {
            val fragment = ComicsFragment()
            val bundle = Bundle()
            bundle.putInt(HERO_ID, heroId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var heroId: Int = 0
    private val comicsViewModel: ComicsViewModel by viewModel()
    private var comicsAdapter: ComicAdapter? = null
    private var _binding: ComicsFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ComicsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        heroId = arguments?.getInt(HERO_ID) ?: 0
        initObservable()
    }

    private fun initObservable() {
        comicsViewModel.getComics().observe(viewLifecycleOwner, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    binding.comicsLabel.visibility = View.GONE
                    binding.comicsDivider.visibility = View.GONE
                    binding.comicsList.visibility = View.GONE
                    binding.loadingComic.comicsShimmerViewContainer.stopShimmer()
                    binding.loadingComic.comicsShimmerViewContainer.visibility = View.GONE
                    Timber.d("ERROR: ${stateModel.errors}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    binding.comicsList.visibility = View.VISIBLE
                    binding.loadingComic.comicsShimmerViewContainer.visibility = View.GONE
                    binding.loadingComic.comicsShimmerViewContainer.stopShimmer()
                    binding.comicsList.setHasFixedSize(true)
                    val layoutManager = LinearLayoutManager(activity)
                    binding.comicsList.layoutManager = layoutManager
                    stateModel.model?.let {
                        comicsAdapter =
                            ComicAdapter(it, this)
                        binding.comicsList.adapter = comicsAdapter
                    }
                }
                ViewStateModel.Status.LOADING -> {
                    binding.loadingComic.comicsShimmerViewContainer.visibility = View.VISIBLE
                    binding.loadingComic.comicsShimmerViewContainer.startShimmer()
                    Timber.d("LOADING: ... ")
                }
            }
        })

        comicsViewModel.loadComics(heroId.toString())
    }

    override fun onComicClicked(comic: Comic) {}
}
