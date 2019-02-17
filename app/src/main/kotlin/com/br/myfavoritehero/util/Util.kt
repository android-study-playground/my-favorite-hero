package com.br.myfavoritehero.util

import java.security.MessageDigest

private const val TYPE_MD5 = "MD5"
private const val HEX_CHARS = "0123456789abcdef"

class Util {

    companion object {

        fun md5(string: String): String {

            val bytes = MessageDigest
                .getInstance(TYPE_MD5)
                .digest(string.toByteArray())
            val result = StringBuilder(bytes.size * 2)

            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }

            return result.toString()
        }
    }
}