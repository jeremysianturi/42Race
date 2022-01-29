package com.example.fortytworace.ui.businesslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.businesses.BusinessesUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class BusinessViewModel @ViewModelInject constructor(private val businessesUsecase: BusinessesUsecase) :
    ViewModel() {

//    // search by business name
//    val searchQueryName = MutableStateFlow("")
//    private val businessesFlowName = searchQueryName.flatMapLatest { businessesUsecase.getSearchBusinesses("name",it) }
//    val searchName = businessesFlowName.asLiveData()
//
//    // search by business zipcode
//    val searchQueryZipCode = MutableStateFlow("")
//    private val businessesFlowZipCode = searchQueryZipCode.flatMapLatest { businessesUsecase.getSearchBusinesses("zipCode",it) }
//    val searchZipCode = businessesFlowZipCode.asLiveData()

    // search by
    val searchQuery = MutableStateFlow("")
    private fun businessesFlow(searchBy: String, sortBy: String) = searchQuery.flatMapLatest { businessesUsecase.getSearchBusinesses(searchBy,it,sortBy) }
    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()



    fun getBusinesses(term: String, latitude: String, longitude: String) =
        businessesUsecase.getBusinesses(term, latitude, longitude).asLiveData()
}