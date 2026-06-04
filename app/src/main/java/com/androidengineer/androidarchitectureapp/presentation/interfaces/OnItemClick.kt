package com.androidengineer.androidarchitectureapp.presentation.interfaces

import com.androidengineer.androidarchitectureapp.presentation.model.Post

interface OnItemClick {
    fun onItemClick(item: Post);
}