package com.rjnr.screens.ui.domain.mapper

import com.rjnr.networking.model.LocationDTO
import com.rjnr.screens.ui.domain.Location

fun LocationDTO.toEntity(): Location {
    return Location(
        name = name,
        url = url,
    )
}
