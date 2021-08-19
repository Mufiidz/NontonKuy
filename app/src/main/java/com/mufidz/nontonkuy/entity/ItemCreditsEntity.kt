package com.mufidz.nontonkuy.entity

data class ItemCreditsGroup(
    var title: String? = null,
    var listCredits: List<ItemCreditsEntity>? = emptyList()
)

data class ItemCreditsEntity(
    var image: String? = null,
    var name: String? = null,
    var desc: String? = null
)
