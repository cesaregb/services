package com.il.sod.db.dao.impl

/**
 * Created by cesaregb on 6/9/17.
 */

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(locations = arrayOf("classpath*:applicationContextTest.xml"))
@ActiveProfiles("local")
class Tests {

    @Test
    fun simple() {
        println("algo algo algoooo ")
        assertEquals(4, 2 * 2)
    }

    @Test
    fun `Algo por aqui anda mal `() {
        println("algo algo algoooo ")
        assertEquals(4, 2 * 2)
    }
}