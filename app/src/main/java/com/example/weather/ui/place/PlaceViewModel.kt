package com.example.weather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.logic.Repository
import com.example.weather.logic.model.Place
import retrofit2.http.Query

/**
 * Author by CYN, Date on 2021/12/31
 */
class PlaceViewModel : ViewModel(){
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){ query->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

    private val place = MutableLiveData<String>()
    val mSavedPlace = Transformations.switchMap(place){
        Repository.getSavedPlace()
    }
    fun getSavedPlace() {
        place.value = place.value
    }

    private val isSave = MutableLiveData<Boolean>()
    val misPlaceSaved = Transformations.switchMap(isSave){
        Repository.isPlaceSaved()
    }
    fun isPlaceSaved() {
        isSave.value = isSave.value
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

}