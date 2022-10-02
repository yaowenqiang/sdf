package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

class Pwd extends Command {
    override def apply(state: State): State = {
        state.setMessage(state.wd.path)
    }
}
