package com.repleyva.common.utils.converters

enum class ConverterDate(val formatter: String) {
    FULL_DATE("dd MMMM yyyy"),
    SQL_DATE("yyyy-MM-dd"),
}
