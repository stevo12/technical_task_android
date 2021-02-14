package com.sliide.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sliide.myapplication.data.BaseResponseModel
import com.sliide.myapplication.data.UserResponseModel
import com.sliide.myapplication.networking.GoRestClient
import com.sliide.myapplication.networking.GoRestService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private var userList: MutableLiveData<BaseResponseModel> = MutableLiveData()
    var lastUserId: Int = 0

    fun getUsersListObserver(): MutableLiveData<BaseResponseModel> {
        return userList
    }

    fun getUsersList() {
        val retrofitInstance = GoRestClient.retrofitInstance().create(GoRestService::class.java)
        val call = retrofitInstance.getUsers()
        call.flatMap { response ->
            retrofitInstance.getUserFromLastPage(response.metadata.pagination.pages)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getUsersListObserverRx())
    }

    fun addUser(userResponseModel: UserResponseModel) {
        val retrofitInstance = GoRestClient.retrofitInstance().create(GoRestService::class.java)
        retrofitInstance.createNewUser(userResponseModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getUsersListObserverRx())
    }

    private fun getUsersListObserverRx(): Observer<BaseResponseModel> {
        return object : Observer<BaseResponseModel> {
            override fun onSubscribe(d: Disposable) {
                //for loading animation
            }

            override fun onNext(t: BaseResponseModel) {
                userList.postValue(t)

            }

            override fun onError(e: Throwable) {
                userList.postValue(null)
            }

            override fun onComplete() {
            }
        }
    }
}