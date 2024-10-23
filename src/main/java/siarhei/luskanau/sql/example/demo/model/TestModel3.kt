package siarhei.luskanau.sql.example.demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestModel3(
    @SerialName("loan_id") val loanId: String,
    @SerialName("current_balance") val currentBalance: Double?,
)
