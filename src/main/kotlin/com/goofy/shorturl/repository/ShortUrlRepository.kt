package com.goofy.shorturl.repository

import com.goofy.shorturl.entity.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepository : JpaRepository<ShortUrl, Long>
