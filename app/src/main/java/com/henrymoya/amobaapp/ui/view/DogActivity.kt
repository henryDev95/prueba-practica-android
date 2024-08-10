package com.henrymoya.amobaapp.ui.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrymoya.amobaapp.R
import com.henrymoya.amobaapp.core.model.DogRace
import com.henrymoya.amobaapp.core.adapter.RaceDogAdapter
import com.henrymoya.amobaapp.core.sesion.SessionManagement
import com.henrymoya.amobaapp.databinding.ActivityDogBinding

class DogActivity : AppCompatActivity() {
    lateinit var binding:ActivityDogBinding
    lateinit var raceDogAdapter: RaceDogAdapter
    private var raceDogList:MutableList<DogRace> = mutableListOf()
    lateinit var  sessionManagement : SessionManagement
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManagement = SessionManagement(this)
        val layoutManager = LinearLayoutManager(this)
        binding.dogList.layoutManager = layoutManager
        binding.dogList.isVisible = true
        binding.viewDogList.isVisible=false
        raceDogAdapter = RaceDogAdapter(raceDogList){
            clickItem(it.race)
        }
        binding.dogList.adapter = raceDogAdapter

        binding.btNewRaceDog.setOnClickListener {
            showDialogAlert()
        }

        binding.logOut.setOnClickListener {
            logoutUser()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showDialogAlert() {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.form_add, null)
        val inputRace = view.findViewById<EditText>(R.id.input_race)

        builder.setView(view)
            .setTitle(R.string.text_title_form)
            .setPositiveButton(R.string.accept) { dialog, _ ->
                val race = inputRace.text.toString()

                val raceDog = DogRace(
                    id = raceDogList.size,
                    race = race
                )
                raceDogList.add(raceDog)
                raceDogAdapter.notifyItemInserted(3)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun clickItem(race: String) {
        var intent = Intent(this,RaceDogActivity::class.java)
        intent.putExtra("race", race)
        startActivity(intent)
    }

    fun logoutUser(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.title_logout)
            .setMessage(R.string.title_logout_message)
            .setPositiveButton(R.string.accept,
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                    sessionManagement.logoutUser()
                    val intent= Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                })
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
        builder.show()
    }

}

