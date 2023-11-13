package com.microcommerce.common.data;

public class ProductErrorMessages {
    public static final String SKU_CODE_LENGTH = "SKU code must be between 8 and 12 characters long";
    public static final String SKU_CODE_PRESENT = "Product with SKU code '%s' already exists";
    public static final String PRODUCT_WITH_SKU_NOT_FOUND = "Product with SKU code '%s' doesn't exist";
    public static final String PRODUCT_NOT_IN_STOCK = "Product with SKU code '%s' is not in stock";
    public static final String SKU_CODE_BLANK = "SKU code can't be null or empty";
    public static final String NAME_LENGTH = "Product name must be between 3 and 60 characters long";
    public static final String NAME_BLANK = "Product name can't be null or empty";
    public static final String PRICE_MIN = "Price can't be less than 1.0";
    public static final String PRICE_MAX = "Price can't be more than 9999999";
    public static final String PRICE_NULL = "Price can't be null or empty";
    public static final String DESCRIPTION_LENGTH = "Description can't contain more than 250 characters";
    public static final String QUANTITY_NULL = "Quantity can't be null or empty";
    public static final String QUANTITY_NEGATIVE = "Quantity can't be negative";
    public static final String IMAGE_URL_INVALID = "Image URL must be valid RFC2396 URL";
}
