package com.example.labapi.api.controller

import com.example.labapi.api.entity.WeatherK
import com.example.labapi.api.exceptions.NoArgumentsException
import com.example.labapi.api.exceptions.NoSuchCityException
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.*

@RestController
@RequestMapping("/api/v2/current/")
class MainControllerK {
    @Value("\${appid}")
    var token: String? = null

    @Value("\${site}")
    var site: String? = null
    @GetMapping("/")
    @Throws(IOException::class)
    fun hello(@RequestParam(value = "city", required = false) city: String): ResponseEntity<*> {
        if (StringUtils.isBlank(city)) {
            throw NoArgumentsException("City not set")
        }
        try {
            val client = OkHttpClient().newBuilder()
                    .build()
            val request: Request = Request.Builder()
                    .url("$site$city&appid=$token&units=metric")
                    .method("GET", null)
                    .build()
            val response = client.newCall(request).execute()
            if (response.code == 404) throw NoSuchCityException("No such city")
            if (response.code == 200) {
                val weather = WeatherK()

                weather.city = city
                val responseAsString = Objects.requireNonNull(response.body)!!.string()
                val indexOfTemp = responseAsString.indexOf("temp")
                val indexOfTempEnd = responseAsString.indexOf(',', indexOfTemp)
                val substring = responseAsString.substring(indexOfTemp - 1, indexOfTempEnd)
                weather.temperature = substring.substring(substring.indexOf(":") + 1).toDouble()
                weather.unit = "celsius"
                return ResponseEntity.ok().body(weather)
            }
        } catch (e: NoArgumentsException) {
            return ResponseEntity.badRequest().body("No city set")
        }
        return ResponseEntity.accepted().body("Hmm")
    }
}