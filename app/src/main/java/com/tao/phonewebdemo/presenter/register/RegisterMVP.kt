package com.tao.phonewebdemo.presenter.register

import com.tao.phonewebdemo.model.remote.data.user.UserData

interface RegisterMVP {
    interface RegisterPresenter{
        fun sendRegisterData(data: UserData)
    }
    interface RegisterView{
        fun setResultRegister(status:Int, message:String)
    }
}