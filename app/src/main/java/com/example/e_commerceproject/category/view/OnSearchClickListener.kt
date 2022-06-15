package com.example.e_commerceproject.category.view

interface OnSearchClickListener {
    fun onQueryTextSubmit(query: String?): Boolean
    fun onQueryTextChange(newText: String?): Boolean
}