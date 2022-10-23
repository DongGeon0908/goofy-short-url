package com.goofy.shorturl.entity

import com.goofy.shorturl.entity.vo.EncoderType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

// TODO : INDEX는??
@Entity
@Table(name = "short_url")
class ShortUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(name = "encoder_type")
    val encoderType: EncoderType,

    @Column(name = "origin_url")
    val originUrl: String,

    @Column(name = "encoded_url")
    val encodedUrl: String
) : BaseEntity()
