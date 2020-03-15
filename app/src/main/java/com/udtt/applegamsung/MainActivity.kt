package com.udtt.applegamsung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.databinding.ActivityMainBinding
import com.udtt.applegamsung.util.log
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val tempRepository: CategoriesRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

//        db.collection("categories").get()
//            .addOnSuccessListener {
//                Log.d(
//                    "Malibin Debug",
//                    "${it.documents.map {
//                        it.toObject(Category::class.java)
//                    }}"
//                )
//
//
//                for (document in it.documents) {
//                    db.collection("categories")
//                        .document(document.id)
//                        .collection("products")
//                        .get()
//                        .addOnSuccessListener {
//                            log("${it.documents.map { it.toObject(Product::class.java) }}")
//                        }
//                }
//            }

        binding.button.setOnClickListener {
            tempRepository.getCategories {
                log("$it")
            }
        }

    }
}
