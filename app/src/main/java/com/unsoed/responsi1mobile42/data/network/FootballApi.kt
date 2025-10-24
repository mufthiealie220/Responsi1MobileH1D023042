package com.unsoed.responsi1mobile42.data.network

import com.unsoed.responsi1mobile42.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballApi {
    @GET("v4/teams/{teamId}")
    suspend fun getTeam(
        @Header("X-Auth-Token") token: String,
        @Path("teamId") teamId: Int
    ): Response<TeamResponse>
}