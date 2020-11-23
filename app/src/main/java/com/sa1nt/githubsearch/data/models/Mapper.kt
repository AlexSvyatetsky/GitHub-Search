package com.sa1nt.githubsearch.data.models

interface Mapper<A, B> {
    @Throws(RuntimeException::class)
    fun transform(obj: A?): List<B>
}