package com.culturapi.CulturApi.business.Login

import com.culturapi.CulturApi.model.LoginRequest
import com.culturapi.CulturApi.model.LoginResponse

interface ILoginBusiness {
    fun load(loginRequest: LoginRequest): LoginResponse
}