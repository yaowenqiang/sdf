package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State
import com.jack.scala.oop.files.Directory

class Mkdir(name: String) extends Command {
    override def apply(state: State): State = {
        val wd = state.wd
        if (wd.hasEntry(name)) {
            state.setMessage("entry " + name + " already exists")
        } else if (name.contains(Directory.SEPARATOR)) {
            state.setMessage(s"${name} must not contains ${Directory.SEPARATOR} separator")
        } else if (checkIllegal(name)) {
            state.setMessage(s"${name}: Illegal entry name!")
        } else {
            doMkdir(name, state)
        }

    }

    def checkIllegal(name: String) : Boolean = {
        name.contains(".")
    }

    def doMkdir(str: String, state: State)  : State = {
        ???
    }
}
