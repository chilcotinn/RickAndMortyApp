package com.chilcotin.rickandmortyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.chilcotin.rickandmortyapp.entities.CharacterModel
import com.chilcotin.rickandmortyapp.screens.MainScreen
import com.chilcotin.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.chilcotin.rickandmortyapp.viewmodels.MainViewModel
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestData(1)

        setContent {
            RickAndMortyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding, mainViewModel)
                }
            }
        }
    }

    private fun requestData(page: Int) {
        val url = "https://rickandmortyapi.com/api/character?page=$page"
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET, url, { result ->
                parseData(result)
            },
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }

    private fun parseData(result: String) {
        val mainObject = JSONObject(result)
        val list = ArrayList<CharacterModel>()
        val charactersArray = mainObject.getJSONArray("results")

        for (i in 0 until charactersArray.length()) {
            val character = charactersArray[i] as JSONObject

            val item = CharacterModel(
                character.getString("name"),
                character.getString("status"),
                character.getString("species"),
                character.getString("gender"),
                character.getJSONObject("origin").getString("name"),
                character.getString("image"),
                character.getInt("id")
            )
            list.add(item)
        }
        mainViewModel.liveData.value = list
    }
}