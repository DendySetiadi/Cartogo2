// Search.kt
package com.example.cartogo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class Search : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase using the application context
        FirebaseApp.initializeApp(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Fetch data from Firestore
        fetchDataFromFirestore()

        return view
    }

    private fun fetchDataFromFirestore() {
        // Fetch data from Firestore
        val firestore = FirebaseFirestore.getInstance()
        val mobilCollection = firestore.collection("mobil")

        // Example: You can dynamically add CardViews based on the retrieved data
        mobilCollection.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val merk = document.getString("merk") ?: ""
                    val gambar = document.getString("gambarUrl") ?: ""
                    val harga = document.getLong("harga")?.toInt() ?: 0

                    // Example: You can dynamically create CardViews here and add them to the LinearLayout
                    val cardView = createCardView(merk, gambar, harga)
                    val linearLayout = view?.findViewById<LinearLayout>(R.id.linearLayoutContainer)
                    linearLayout?.addView(cardView)
                }
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
        Log.d("SearchFragment", "Fetching data from Firestore")
        mobilCollection.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    // ...
                }
            }
            .addOnFailureListener { exception ->
                Log.e("SearchFragment", "Error fetching data: $exception")
            }

    }

    // Example: Create a CardView programmatically
    private fun createCardView(merk: String, gambar: String, harga: Int): CardView {
        val cardView = CardView(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.card_margin_bottom)
        cardView.layoutParams = layoutParams

        val relativeLayout = RelativeLayout(requireContext())
        cardView.addView(relativeLayout)

        val imageView = ImageView(requireContext())
        imageView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.card_image_height)
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        // Set imageView attributes (id, width, height, scaleType, src, etc.)
        // For example, if you are using Picasso for image loading:
        // Picasso.get().load(gambar).placeholder(R.drawable.placeholder_image).into(imageView)
        relativeLayout.addView(imageView)

        val textMerk = TextView(requireContext())
        textMerk.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textMerk.text = merk
        // Set textMerk attributes (id, width, height, text, textSize, etc.)
        relativeLayout.addView(textMerk)

        val textHarga = TextView(requireContext())
        textHarga.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textHarga.text = "Harga: $harga"
        // Set textHarga attributes (id, width, height, text, textSize, etc.)
        relativeLayout.addView(textHarga)
// Load gambar dari URL menggunakan Picasso
        Picasso.get().load(gambar).placeholder(R.drawable.placeholder_image).into(imageView)

        // Set values for imageView, textMerk, and textHarga based on the passed parameters

        return cardView

    }

}
