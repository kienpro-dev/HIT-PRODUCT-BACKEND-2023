package com.example.projectbase.constant;

public class UrlConstant {

    public static class Auth {
        private static final String PRE_FIX = "/auth";

        public static final String LOGIN = PRE_FIX + "/login";
        public static final String LOGOUT = PRE_FIX + "/logout";
        public static final String REGISTER = PRE_FIX + "/register";
        public static final String FORGET_PASSWORD = PRE_FIX + "/forget-password";

        private Auth() {
        }
    }

    public static class User {
        private static final String PRE_FIX = "/user";

        public static final String GET_USERS = PRE_FIX;
        public static final String GET_USER = PRE_FIX + "/{userId}";
        public static final String GET_CURRENT_USER = PRE_FIX + "/current";

        public static final String FIND_PRODUCT_INFO = PRE_FIX + "/find-product-info";
        public static final String GET_PRODUCT_DETAIL = PRE_FIX + "/get-product-detail/{productId}";

        private User() {
        }
    }

    public static class Customer {
        private static final String PRE_FIX = "/customer";

        public static final String GET_CUSTOMERS = PRE_FIX;
        public static final String GET_CUSTOMER = PRE_FIX + "/{customerId}";
        public static final String UPDATE_CUSTOMER = PRE_FIX + "/{customerId}";
        public static final String DELETE_CUSTOMER = PRE_FIX + "/{customerId}";

        private Customer() {
        }
    }

    public static class Product {
        private static final String PRE_FIX = "/product";

        public static final String GET_PRODUCTS = PRE_FIX;
        public static final String GET_PRODUCT = PRE_FIX + "/{productId}";
        public static final String UPDATE_PRODUCT = PRE_FIX + "/{productId}";
        public static final String DELETE_PRODUCT = PRE_FIX + "/{productId}";
        public static final String GET_PRODUCTS_BY_CATEGORY = PRE_FIX + "get_products_by_category" + "/{categoryId}";
        public static final String GET_PRODUCTS_BY_SHOP = PRE_FIX + "get_products_by_shop" + "/{shopId}";

        private Product() {
        }
    }

    public static class Category {
        private static final String PRE_FIX = "/category";

        public static final String GET_CATEGORIES = PRE_FIX;
        public static final String GET_CATEGORY = PRE_FIX + "/{categoryId}";
        public static final String UPDATE_CATEGORY = PRE_FIX + "/{categoryId}";
        public static final String DELETE_CATEGORY = PRE_FIX + "/{categoryId}";
        public static final String GET_CATEGORIES_BY_SHOP=PRE_FIX+"get_categories_by_shop"+"/{shopId}";
        private Category() {
        }
    }

    public static class Shop {
        private static final String PRE_FIX = "/shop";

        public static final String GET_SHOPS = PRE_FIX;
        public static final String GET_SHOP = PRE_FIX + "/{shopId}";
        public static final String UPDATE_SHOP = PRE_FIX + "/{shopId}";
        public static final String DELETE_SHOP = PRE_FIX + "/{shopId}";

        private Shop() {
        }
    }

    public static class Cart {
        private static final String PRE_FIX = "/cart";

        public static final String ADD_PRODUCT_TO_CART = PRE_FIX + "/{cartId}" + "/products";
        public static final String GET_CART_INFO = PRE_FIX + "/{cartId}";
        public static final String DELETE_CART_INFO = PRE_FIX + "/{cartId}";
        public static final String UPDATE_CART_INFO = PRE_FIX + "/{cartId}" + "/products" + "/{productId}";

        private Cart() {
        }
    }
}
