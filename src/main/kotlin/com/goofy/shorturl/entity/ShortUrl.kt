package com.goofy.shorturl.entity

import com.goofy.shorturl.entity.vo.EncoderType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "short_url")
class ShortUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,

    var description: String? = null,

    @Column(name = "type")
    var type: EncoderType,

    @Column(name = "url")
    var url: String
) : BaseEntity()
