package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

//trait Command extends (state => state) {
trait Command {
    def apply(state: State) : State
}
object Command {
    val MKDIR = "mkdir"
    val LS = "ls"
    val PWD = "pwd"
    val TOUCH = "touch"
    val CD = "cd"
    val RM = "rm"
    val ECHO = "echo"
    val CAT = "cat"
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
//        else tokens[1] match {
//            case "MKDIR" =>
//                if (tokens.length < 2) inCompleteCommand(MKDIR)
//                else new Mkdir(tokens(1))
        //            case "LS" =>
//        }
        else if (MKDIR.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Mkdir(tokens(1))
        }
        else if (LS.equals(tokens(0))) {
            new Ls
        }
        else if (PWD.equals(tokens(0))) {
            new Pwd
        }
        else if (TOUCH.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Touch(tokens(1))
        }
        else if (CD.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Cd(tokens(1))
        }
        else if (RM.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Rm(tokens(1))
        }
        else if (ECHO.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Echo(tokens.tail)
        }
        else if (CAT.equals(tokens(0))) {
            if (tokens.length < 2) inCompleteCommand(MKDIR)
            else new Cat(tokens(1))
        }
        else new UnknownCommand

    }
}