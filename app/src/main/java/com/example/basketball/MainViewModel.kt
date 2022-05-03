package com.example.basketball

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var localScore: MutableLiveData<Int> = MutableLiveData()
    var visitorScore: MutableLiveData<Int> = MutableLiveData()

    init {
        resetPoint()
    }

    fun resetPoint(){
        localScore.value  = 0
        visitorScore.value = 0
    }

    fun addPointToScore( point: Int, isLocal: Boolean ) {
        if (isLocal){
            localScore.value = localScore.value!! + point
        }else {
            visitorScore.value = visitorScore.value!! + point
        }
    }

    fun removePointLocal(  ) {
        if ( localScore.value!! > 0){
            localScore.value = localScore.value!! - 1
        }
    }

     fun removePointVisitor( ) {
        if ( visitorScore.value!! > 0){
            visitorScore.value = visitorScore.value!! - 1
        }
    }

}