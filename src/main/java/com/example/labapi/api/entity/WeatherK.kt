package com.example.labapi.api.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
class WeatherK {
    var city: String? = null
    var unit: String? = null
    var temperature: Double? = null
    override fun toString(): String {
        return """
            {"city":${city?.let { wrap(it) }},
            "unit":${unit?.let { wrap(it) }},
            "temperature":${temperature}}
            """.trimIndent()
    }

    companion object {
        private fun wrap(a: String): String {
            return "\"" + a + "\""
        }
    }
}