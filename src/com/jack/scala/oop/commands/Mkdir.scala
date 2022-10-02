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
            doMkdir(state, name)
        }

    }

    def checkIllegal(name: String) : Boolean = {
        name.contains(".")
    }

    def doMkdir(state: State, name: String)  : State = {
        def updateStructure(currentDirectory: Directory, path: List[String], newEntry: Directory) :Directory =
            if (path.isEmpty) currentDirectory.addEntry(newEntry)
            else {
                println(path)
                println(path.head)
                println(path.head.isEmpty)
                println(currentDirectory.findEntry(path.head))
                val oldEntry = currentDirectory.findEntry(path.head).asDirectory
                currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
            }

        val wd = state.wd
        val allDirsInPath = wd.getAllFoldersInPath

        val newDir = Directory.empty(wd.path, name)

        val newRoot = updateStructure(state.root, allDirsInPath, newDir)
        val newWd = newRoot.findDescendant(allDirsInPath)
        State(newRoot, newWd)
    }
}
