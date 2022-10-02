package com.jack.scala.oop.filesystem

import com.jack.scala.oop.commands.Command
import com.jack.scala.oop.files.Directory

import java.util.Scanner

object Filesystem extends App {
    val root = Directory.ROOT
//    var state = State(root, root)
//    val scanner = new Scanner(System.in)
//    while (true) {
////        println("$ ")
//        state.show
//        val input = scanner.nextLine()
//        state = Command.from(input).apply(state)
//
//    }

    io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
        currentState.show
        Command.from(newLine).apply(currentState)
    })
}
