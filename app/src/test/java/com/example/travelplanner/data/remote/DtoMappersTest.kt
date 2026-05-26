package com.example.travelplanner.data.remote

import com.example.travelplanner.data.remote.dto.CountryDto
import com.example.travelplanner.data.remote.dto.CurrencyDto
import com.example.travelplanner.data.remote.dto.FlagsDto
import com.example.travelplanner.data.remote.dto.NameDto
import com.example.travelplanner.data.remote.mapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DtoMappersTest {

    private val czechDto = CountryDto(
        name = NameDto(common = "Czechia", official = "Czech Republic"),
        capital = listOf("Prague"),
        region = "Europe",
        currencies = mapOf("CZK" to CurrencyDto(name = "Czech koruna", symbol = "Kč")),
        flags = FlagsDto(png = "https://flagcdn.com/w320/cz.png", svg = null),
        population = 10_900_000L
    )

    @Test
    fun `toDomain возвращает common name`() {
        assertEquals("Czechia", czechDto.toDomain().name)
    }

    @Test
    fun `toDomain возвращает official name как country`() {
        assertEquals("Czech Republic", czechDto.toDomain().country)
    }

    @Test
    fun `toDomain возвращает первую столицу`() {
        assertEquals("Prague", czechDto.toDomain().capital)
    }

    @Test
    fun `toDomain форматирует валюту с символом`() {
        assertEquals("Czech koruna (Kč)", czechDto.toDomain().currency)
    }

    @Test
    fun `toDomain возвращает png флага`() {
        assertEquals("https://flagcdn.com/w320/cz.png", czechDto.toDomain().flagUrl)
    }

    @Test
    fun `toDomain возвращает население`() {
        assertEquals(10_900_000L, czechDto.toDomain().population)
    }

    @Test
    fun `toDomain без столицы возвращает null`() {
        assertNull(czechDto.copy(capital = null).toDomain().capital)
    }

    @Test
    fun `toDomain без валюты возвращает null`() {
        assertNull(czechDto.copy(currencies = null).toDomain().currency)
    }
}
