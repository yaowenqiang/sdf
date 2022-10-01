package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

class UnknownCommand extends Command {
    override def apply(state: State)= {
        state.setMessage("Command not found")
    }
}
