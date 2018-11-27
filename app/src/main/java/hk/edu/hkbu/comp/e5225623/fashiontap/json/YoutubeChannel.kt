package hk.edu.hkbu.comp.e5225623.fashiontap.json

data class YoutubeChannelResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)