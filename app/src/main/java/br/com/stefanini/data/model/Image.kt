package br.com.stefanini.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image(
    @SerializedName("id") var id: String,
    @SerializedName("type") var type: String,
    @SerializedName("link") var link: String,
    @SerializedName("title") var title: String,
    @SerializedName("descriptiom") var description: String
) : Serializable