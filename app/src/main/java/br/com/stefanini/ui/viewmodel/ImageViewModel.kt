package br.com.stefanini.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.stefanini.R
import br.com.stefanini.data.api.controller.ImgurController
import br.com.stefanini.data.api.service.Api
import br.com.stefanini.data.model.ImageData
import br.com.stefanini.data.response.ErrorDataResponse
import br.com.stefanini.data.response.ImgurDataResponse
import io.andref.rx.network.RxNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private var isConnected: Boolean = true
    private val context = getApplication() as Context

    var imageLiveData = MutableLiveData<List<ImageData>>()
    var errorLivaData = MutableLiveData<ErrorDataResponse>()

    init {
        RxNetwork.connectivityChanges(context, context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).subscribe {connected ->
            if (!connected) errorLivaData.value = ErrorDataResponse(context.getString(R.string.no_internet_connection))
            this.isConnected = connected
        }
    }

    /**
     * Search images by specific category
     * @param category image category
     */
    fun searchImages(query: String) {
        if (isConnected) {
            Api.createService(ImgurController::class.java, "Client-ID 1ceddedc03a5d71")
                .searchImages(query)
                .enqueue(object : Callback<ImgurDataResponse> {
                    override fun onResponse(call: Call<ImgurDataResponse>, response: Response<ImgurDataResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                imageLiveData.value = response.body()?.data
                                errorLivaData.value = null
                            }
                        } else {
                            errorLivaData.value = ErrorDataResponse(context.getString(R.string.the_images_could_not_be_loaded))
                        }
                    }
                    override fun onFailure(call: Call<ImgurDataResponse>, t: Throwable) {
                        errorLivaData.value = ErrorDataResponse(context.getString(R.string.the_images_could_not_be_loaded))
                    }
                })

        } else {
            errorLivaData.value = ErrorDataResponse(context.getString(R.string.no_internet_connection))
        }
    }
}