package com.example.coagusearch.network.PatientData.model

import com.example.coagusearch.network.shared.response.ApiResponse
import java.io.Serializable

data class PatientDataResponse (
    val lastDataAnalysisTime: LastDataAnalysisTimeResponse = LastDataAnalysisTimeResponse(),
    val oldAnalysis : List<ApiResponse> = listOf()
):Serializable

data class LastDataAnalysisTimeResponse (
    val day: Int = 21,
    val month: Int = 3,
    val year: Int = 2020
):Serializable
