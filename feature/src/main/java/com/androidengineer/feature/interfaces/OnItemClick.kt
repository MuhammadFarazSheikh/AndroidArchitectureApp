package com.androidengineer.feature.interfaces

import com.androidengineer.core.domain.model.Post

interface OnItemClick {
    fun onItemClick(item: Post);
}