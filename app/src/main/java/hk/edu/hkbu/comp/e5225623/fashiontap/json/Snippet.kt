package hk.edu.hkbu.comp.e5225623.fashiontap.json

data class Snippet(
    val country: String,
    val customUrl: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
)