package siarhei.luskanau.sql.example.demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestModel2(
    @SerialName("loan_id") val loanId: String,
    @SerialName("duedate") val dueDate: String?,
    @SerialName("payment_amount") val paymentAmount: Double?,
)
