package com.alvachien;

import com.alvachien.product.Product;

import org.junit.jupiter.params.converter.TypedArgumentConverter;

public class ProductArgumentConverter extends TypedArgumentConverter<String, Product> {
    protected ProductArgumentConverter() {
        super(String.class, Product.class);
    }

    @Override
    protected Product convert(String source) {
        String[] strStrings = source.split(";");

        Product product = new Product(
            Long.parseLong(strStrings[0]), 
            strStrings[1], 
            Double.parseDouble(strStrings[2]));

        return product;
    }
}
