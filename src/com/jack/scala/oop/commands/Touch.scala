package com.jack.scala.oop.commands
import com.jack.scala.oop.files.DirEntry
import com.jack.scala.oop.files.Directory
import com.jack.scala.oop.filesystem.State

import com.jack.scala.oop.files.File

class Touch(name: String) extends CreateEntry(name) {
    override def createSpecificEntry(state: State, name: String): DirEntry =
        File.empty(state.wd.path, name)
}
