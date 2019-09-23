package com.br.myfavoritehero.features.listComics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.ComicEventListener
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.features.listComics.adapter.ComicAdapter
import com.br.myfavoritehero.features.listComics.viewModel.ComicsViewModel
import kotlinx.android.synthetic.main.comic_list_loading.*
import kotlinx.android.synthetic.main.comics_fragment.*
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        heroId = arguments?.getInt(HERO_ID) ?: 0
        initObservable()
    }

    private fun initObservable() {
        comicsViewModel.getComics().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    comics_label.visibility = View.GONE
                    comics_divider.visibility = View.GONE
                    comics_list.visibility = View.GONE
                    shimmer_view_container.stopShimmer()
                    shimmer_view_container.visibility = View.GONE
                    Timber.d("ERROR: ${stateModel.errors}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    comics_list.visibility = View.VISIBLE
                    shimmer_view_container.visibility = View.GONE
                    shimmer_view_container.stopShimmer()
                    comics_list.setHasFixedSize(true)
                    val layoutManager = LinearLayoutManager(activity)
                    comics_list.layoutManager = layoutManager
                    stateModel.model?.let {
                        comicsAdapter =
                            ComicAdapter(it, this)
                        comics_list.adapter = comicsAdapter
                    }
                }
                ViewStateModel.Status.LOADING -> {
                    shimmer_view_container.visibility = View.VISIBLE
                    shimmer_view_container.startShimmer()
                    Timber.d("LOADING: ... ")
                }
            }
        })

        comicsViewModel.loadComics(heroId.toString())
    }

    override fun onComicClicked(comic: Comic) {}
}
