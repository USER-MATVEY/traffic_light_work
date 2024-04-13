package com.example.transport_programm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.transport_programm.databinding.ActivityMainBinding
import com.example.transport_programm.databinding.DialogAddCharacterBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter

    private val data = mutableListOf(
        Character(id= Random.nextLong(), name="Henry", isCustom=false),
        Character(id= Random.nextLong(), name="Julia", isCustom=false),
        Character(id= Random.nextLong(), name="Dzosh", isCustom=false),
        Character(id= Random.nextLong(), name="Kyle", isCustom=false),
        Character(id= Random.nextLong(), name="Liza", isCustom=false),
        Character(id= Random.nextLong(), name="Diana", isCustom=false),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        binding.addButton.setOnClickListener { onAddPressed() }
    }

    private fun setupList(){
        adapter = CharacterAdapter(data){
            deleteCharacter(it)
        }
        binding .spinnerList.adapter = adapter
    }

    private fun onAddPressed(){
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Create Character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add"){
                d, which -> val name = dialogBinding.CharacterNameEditText.text
                if (name.isNotBlank()){
                    createCharacter(name)
                }
            }
            .create()
        dialog.show()
    }

    //TODO: implement things starting with 41 slide + some others

}