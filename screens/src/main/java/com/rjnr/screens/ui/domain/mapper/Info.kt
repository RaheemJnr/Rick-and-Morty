package com.rjnr.screens.ui.domain.mapper

import com.rjnr.networking.model.InfoDTO
import com.rjnr.screens.ui.domain.Info

fun InfoDTO.toEntity(): Info {
    return Info(
        count = count,
        next = next,
        pages = pages,
        prev = prev,
    )
}
