package com.example.new_compose.modules.dashboard.data

data class ProductListRes (
    val total: Long,
    val limit: Long,
    val skip: Long,
    val products: List<Product>
)

data class Product (
    val thumbnail: String,
    val images: List<String>,
    val minimumOrderQuantity: Long,
    val rating: Double,
    val returnPolicy: String,
    val description: String,
    val weight: Long,
    val warrantyInformation: String,
    val title: String,
    val tags: List<String>,
    val discountPercentage: Double,
    val reviews: List<Review>,
    val price: Double,
    val meta: Meta,
    val shippingInformation: String,
    val id: Long,
    val availabilityStatus: String,
    val category: String,
    val stock: Long,
    val sku: String,
    val brand: String,
    val dimensions: Dimensions
)

data class Dimensions (
    val depth: Double,
    val width: Double,
    val height: Double
)

data class Meta (
    val createdAt: String,
    val qrCode: String,
    val barcode: String,
    val updatedAt: String
)

data class Review (
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
    val rating: Long,
    val comment: String
)
