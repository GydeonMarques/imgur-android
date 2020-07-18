package br.com.stefanini.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageData(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("descriptiom") var description: String,
    @SerializedName("images") var images: List<Image>
) : Serializable