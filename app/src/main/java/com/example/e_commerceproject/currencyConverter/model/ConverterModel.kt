package com.example.e_commerceproject.currencyConverter.model

data class ConverterModel(
    var from :String,
    var to :String,
    var amount :Long,
    var timestamp :Long,
    var rate :Long,
    var date :String,
    var result :String
)
