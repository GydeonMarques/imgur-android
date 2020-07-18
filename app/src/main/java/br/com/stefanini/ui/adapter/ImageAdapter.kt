package br.com.stefanini.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.stefanini.LoadImageUtils
import br.com.stefanini.R
import br.com.stefanini.data.model.Image
import br.com.stefanini.data.model.ImageData
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(private val onItemClickListener: ((image: Image) -> Unit)) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var imageData: List<ImageData> = emptyList()

    fun setImageData(images: List<ImageData>) {
        this.imageData = images
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(imageData[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return if (!imageData.isNullOrEmpty()) imageData.size else 0
    }


    class ViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        private var image: ImageView? = itemView.image_view_poster

        fun bindView(imageData: ImageData, onItemClickListener: ((image: Image) -> Unit)) {
            if (!imageData.images.isNullOrEmpty()){
                if (imageData.images[0].type.contains("image")){
                    //As an example, we will take only the first image from the list
                    this.image?.setOnClickListener { onItemClickListener.invoke(imageData.images[0]) }
                    LoadImageUtils.loadImageAndApplyToImageView(context, this.image!!, imageData.images[0].link, context.getDrawable(R.drawable.image_default))
                }
            }
        }
    }
}