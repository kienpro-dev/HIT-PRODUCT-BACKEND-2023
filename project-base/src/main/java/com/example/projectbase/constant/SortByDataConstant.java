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

}
