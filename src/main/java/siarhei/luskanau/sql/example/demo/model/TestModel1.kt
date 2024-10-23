package siarhei.luskanau.sql.example.demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestModel1(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String?,
    @SerialName("sold_count") val soldCount: Int?,
)
