package com.example.minimessengerfirebase.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minimessengerfirebase.adapter.ChatAdapter
import com.example.minimessengerfirebase.databinding.FragmentChatBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = ChatAdapter()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListener()
        getCollectionData()
    }

    private fun initialize() {
        binding.recView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setUpListener() {
        binding.buttomInput.setOnClickListener {
            val collectionRef = db.collection("user")
            val message = hashMapOf(
                "message" to binding.edText.text.toString()
            )
                collectionRef.add(message).addOnSuccessListener {
                Log.e("Success", "$it")
            }
        }
    }

    private fun getCollectionData() {
        val collectionRef = db.collection("user")
        collectionRef.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (value != null) {
                val messages = ArrayList<String>()
                for (doc in value) {
                    doc.getString("message")?.let {
                        messages.add(it)
                    }
                }
                chatAdapter.setData(messages)
            }
        }
    }
}
