package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

trait Command {
    def apply(state: State) : State
}
object Command {
    def from(input: String) :Command =
        new UnknownCommand
}