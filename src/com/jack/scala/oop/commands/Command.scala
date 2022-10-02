package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

trait Command {
    def apply(state: State) : State
}
object Command {
    val MKDIR = "mkdir"
    val LS = "ls"
    def emptyCommand :Command = new Command {
        override def apply(state: State): State = state
    }
    def inCompleteCommand(name: String) : Command = new Command {
        override def apply(state: State): State = {
            state.setMessage("Incomplete command")
        }
    }
    def from(input: String) :Command = {
        val tokens: Array[String] = input.split(' ')
        if (input.isEmpty || tokens.isEmpty) emptyCommand
        if (tokens.isEmpty)  emptyCommand
        else if (MKDIR.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Mkdir(tokens(1))
        }
        else if (LS.equals(tokens(0))) {
            new Ls
        }
        else new UnknownCommand

    }
}