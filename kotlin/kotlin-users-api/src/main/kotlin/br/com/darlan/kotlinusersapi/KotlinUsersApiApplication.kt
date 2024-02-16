package br.com.darlan.kotlinusersapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinUsersApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinUsersApiApplication>(*args)
}
