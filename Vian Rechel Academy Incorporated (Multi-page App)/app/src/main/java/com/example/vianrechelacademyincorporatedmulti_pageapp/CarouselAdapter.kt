import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.vianrechelacademyincorporatedmulti_pageapp.R

// I created this Adapter class to display a carousel of images in a RecyclerView.
class CarouselAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    // I created this ViewHolder class to hold reference to the image view in each carousel item.
    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.carouselImage)
    }

    // This inflates the layout for each carousel item and create a ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carousel_item, parent, false)
        return CarouselViewHolder(view)
    }

    // This binds the image resource at the current position to the ImageView in the ViewHolder.
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.image.setImageResource(imageList[position])
    }

    // This returns the total number of images in the carousel.
    override fun getItemCount(): Int = imageList.size
}
