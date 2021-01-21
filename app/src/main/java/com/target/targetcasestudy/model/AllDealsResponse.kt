package com.target.targetcasestudy.model

import com.google.gson.annotations.SerializedName

data class AllDealsResponse(

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class RegularPrice(

	@field:SerializedName("amount_in_cents")
	val amountInCents: Int? = null,

	@field:SerializedName("currency_symbol")
	val currencySymbol: String? = null,

	@field:SerializedName("display_string")
	val displayString: String? = null
)

data class SalePrice(

	@field:SerializedName("amount_in_cents")
	val amountInCents: Int? = null,

	@field:SerializedName("currency_symbol")
	val currencySymbol: String? = null,

	@field:SerializedName("display_string")
	val displayString: String? = null
)

data class ProductsItem(

	@field:SerializedName("regular_price")
	val regularPrice: RegularPrice? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null,

	@field:SerializedName("sale_price")
	val salePrice: SalePrice? = null
)
