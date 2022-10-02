package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State
import com.jack.scala.oop.files.{DirEntry, Directory}

class Mkdir(name: String) extends CreateEntry(name) {
    override def createSpecificEntry(state: State, name: String): Directory =
        Directory.empty(state.wd.path, name)
}
