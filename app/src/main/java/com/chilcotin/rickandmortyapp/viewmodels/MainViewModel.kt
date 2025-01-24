package com.chilcotin.rickandmortyapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chilcotin.rickandmortyapp.entities.CharacterModel

class MainViewModel : ViewModel() {
    val liveData = MutableLiveData<List<CharacterModel>>()
}