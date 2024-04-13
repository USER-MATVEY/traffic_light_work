package com.example.transport_programm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.transport_programm.databinding.ItemCharacterLineBinding

typealias OnDeletePressedListener = (Character) -> Unit

class CharacterAdapter(
    private val characters: List<Character>,
    private val onDeletePressedListener: OnDeletePressedListener
): BaseAdapter(), View.OnClickListener {
    override fun getCount(): Int {
        return characters.size
    }

    override fun getItem(p0: Int): Character {
        return characters[p0]
    }

    override fun getItemId(p0: Int): Long {
        return characters[p0].id
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binging = p1?.tag as ItemCharacterLineBinding? ?: createBinding(p2!!.context)
        val characteritem = getItem(p0)
        binging.titleTextView.text = characteritem.name
        binging.deleteImageView.tag = characteritem
        binging.deleteImageView.visibility = if (characteritem.isCustom) View.VISIBLE else View.GONE

        return binging.root
    }

    override fun onClick(p0: View?) {
        val character = p0!!.tag as Character
        onDeletePressedListener.invoke(character)
    }

    private fun createBinding(context: Context): ItemCharacterLineBinding {
        val binding = ItemCharacterLineBinding.inflate(LayoutInflater.from(context))
        binding.deleteImageView.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }

}