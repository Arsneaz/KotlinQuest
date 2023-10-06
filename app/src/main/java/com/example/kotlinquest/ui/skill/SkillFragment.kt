package com.example.kotlinquest.ui.skill

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinquest.R
import com.example.kotlinquest.databinding.FragmentSkillBinding
import kotlin.collections.ArrayList
import java.util.*

class SkillFragment : Fragment() {

    private lateinit var binding : FragmentSkillBinding
    private lateinit var adapter : MyAdapter
    private lateinit var skillArrayList : ArrayList<Skill>
    private lateinit var imageId : IntArray
    private lateinit var heading : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSkillBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        setupRecyclerView()
        setupAdapter()
        setupSearchView()

        adapter.onItemClick = {
            navigateToDetail(it.heading)
        }

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setupAdapter() {
        adapter = MyAdapter(skillArrayList)
        binding.recyclerView.adapter = adapter
    }

    private fun setupSearchView() {
        binding.searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun navigateToDetail(extraName: String){
        findNavController().navigate(SkillFragmentDirections.actionNavSkillToSkillDetail(extraName))
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Skill>()
            for (i in skillArrayList) {
                if (i.heading.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun dataInitialize(){
        skillArrayList = arrayListOf()

        imageId = resources.getIntArray(R.array.image_ids)
        heading = resources.getStringArray(R.array.headings)

        getUserData()
    }

    private fun getUserData() {

        for (i in imageId.indices){
            val skill = Skill(imageId[i],heading[i])
            skillArrayList.add(skill)
        }
    }
}