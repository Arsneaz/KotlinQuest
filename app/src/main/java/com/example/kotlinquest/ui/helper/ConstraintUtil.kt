package com.example.kotlinquest.ui.helper

import androidx.fragment.app.Fragment
import com.example.kotlinquest.R
import com.example.kotlinquest.ui.skill.Skill

object ConstraintUtil {
    fun getSkillData(fragment: Fragment): ArrayList<Skill> {
        val skillArrayList = ArrayList<Skill>()
        val heading = fragment.resources.getStringArray(R.array.headings)
        val images = fragment.resources.getIntArray(R.array.image_ids)
        for (i in images.indices){
            val skill = Skill(images[i],heading[i])
            skillArrayList.add(skill)
        }
        return skillArrayList
    }
}