package com.example.projectbase.constant;

public enum SortByDataConstant implements SortByInterface {

  USER {
    @Override
    public String getSortBy(String sortBy) {
      if ("lastModifiedDate".equals(sortBy)) {
        return "lastModifiedDate";
      }
      return "createdDate";
    }
  },

  CUSTOMER {
    @Override
    public String getSortBy(String sortBy) {
      if ("lastModifiedDate".equals(sortBy)) {
        return "lastModifiedDate";
      } else if ("fullName".equals(sortBy)) {
        return "fullName";
      } else if ("address".equals(sortBy)) {
        return "address";
      } else if ("dob".equals(sortBy)) {
        return "dob";
      }
      return "createdDate";
    }
  },

  PRODUCT {
    @Override
    public String getSortBy(String sortBy) {
      if ("name".equals(sortBy)) {
        return "name";
      }
      return "price";
    }
  },

  CATEGORY {
    @Override
    public String getSortBy(String sortBy) {
      return "name";
    }
  },

  SHOP {
    @Override
    public String getSortBy(String sortBy) {
      return "name";
    }
  }


}
