package br.com.stefanini.data.response

import br.com.stefanini.data.model.ImageData
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImgurDataResponse(
    @SerializedName("data") var data: List<ImageData>
) : Serializable