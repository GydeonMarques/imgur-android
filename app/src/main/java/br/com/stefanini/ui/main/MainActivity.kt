package br.com.stefanini.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.stefanini.R
import br.com.stefanini.data.model.ImageData
import br.com.stefanini.ui.adapter.ImageAdapter
import br.com.stefanini.ui.viewmodel.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.layout_server_error.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imageViewModel: ImageViewModel

    //The category value is fixed just to exemplify the call of the api
    companion object {
        private const val IMAGE_CATEGORY = "cats"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Change layout visibility
        changeLayoutVisibility(isLoading = true)

        //=======================================================================================//

        swipe_refresh_layout?.setColorSchemeResources(
            R.color.refresh_progress_01,
            R.color.refresh_progress_02,
            R.color.refresh_progress_03)
        swipe_refresh_layout?.setOnRefreshListener { imageViewModel.searchImages(IMAGE_CATEGORY) }

        //=======================================================================================//

        imageAdapter = ImageAdapter { image ->
            //Click listener,
        }

        recycler_view_images?.layoutManager = GridLayoutManager(this, 3)
        recycler_view_images?.adapter = imageAdapter
        recycler_view_images?.setHasFixedSize(true)

        //=======================================================================================//

        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageViewModel.imageLiveData.observe(this, Observer {
            it?.let {
                imageAdapter.setImageData(it)
                changeLayoutVisibility(isLoading = false)
            }
        })
        imageViewModel.errorLivaData.observe(this, Observer {
            it?.let {
                changeLayoutVisibility(isLoading = false, isError = true)
            }
        })

        //Search images by specific category
        imageViewModel.searchImages(IMAGE_CATEGORY)
    }


    /**
     * Change layout visibility
     * @param isLoading
     * @param isError
     * @param isEmpty
     */
    private fun changeLayoutVisibility(isLoading: Boolean, isError: Boolean = false) {
        swipe_refresh_layout?.isRefreshing = false
        layout_loading?.visibility = if (isLoading && !isError) View.VISIBLE else View.GONE
        layout_server_error?.visibility = if (!isLoading && isError) View.VISIBLE else View.GONE
        layout_container_images?.visibility = if (!isLoading && !isError) View.VISIBLE else View.GONE
    }
}