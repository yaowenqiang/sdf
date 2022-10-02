package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

class Cat(fileName: String) extends Command {
    override def apply(state: State): State = {
        val wd = state.wd
        val dirEntry = wd.findEntry(fileName)
        if (!dirEntry.isFile || dirEntry == null ) {
            state.setMessage(s"${fileName}: no such file")
        } else {
            state.setMessage(dirEntry.asFile.contents)
        }
    }
}
