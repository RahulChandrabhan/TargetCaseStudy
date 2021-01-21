package com.target.targetcasestudy.model

import com.google.gson.annotations.SerializedName

data class SingleItemResponse(

	@field:SerializedName("regular_price")
	val regularPriceForSingleItem: RegularPriceForSingleItem? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null
)

data class RegularPriceForSingleItem(

	@field:SerializedName("amount_in_cents")
	val amountInCents: Int? = null,

	@field:SerializedName("currency_symbol")
	val currencySymbol: String? = null,

	@field:SerializedName("display_string")
	val displayString: String? = null
)
