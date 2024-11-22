package com.playlisttransfer

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["com.playlisttransfer"])
class Application {
    @Bean
    fun startupRunner() = CommandLineRunner {
        println("Application has started successfully!!")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}