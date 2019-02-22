package com.br.myfavoritehero.data.interfaces

import com.br.myfavoritehero.data.models.Comic


interface ComicEventListener{
    fun onComicClicked(comic: Comic)
}