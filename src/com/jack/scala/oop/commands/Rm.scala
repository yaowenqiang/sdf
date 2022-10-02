package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State
import com.jack.scala.oop.files.Directory


class Rm(name: String) extends Command {
    def apply(state: State): State = {
        val wd = state.wd
        var absPath = if (name.startsWith(Directory.SEPARATOR)) name
        else if (wd.isRoot) wd.path + name
        else wd.path + Directory.SEPARATOR + name

        if(Directory.ROOT_PATH.eq(absPath))
            state.setMessage("Nuclear War not supported yet")
        else
            doRm(state, absPath)
    }

    def doRm(state: State, path: String) :State = {
        def rmHelper(currentDirectory: Directory, path: List[String]) :Directory =  {
            if(path.isEmpty) currentDirectory
            else if (path.tail.isEmpty) currentDirectory.removeEntry(path.head)
            else  {
                val nextDirectory = currentDirectory.findEntry(path.head)
                if (!nextDirectory.isDirectory) currentDirectory
                else {
                    val newNextDirectory = rmHelper(nextDirectory.asDirectory, path.tail)
                    if (newNextDirectory == nextDirectory) currentDirectory
                    else {
                        currentDirectory.replaceEntry(path.head, newNextDirectory)
                    }
                }
            }


        }

        val tokens = path.substring(1).split(Directory.SEPARATOR).toList
        val newRoot: Directory = rmHelper(state.root, tokens)
        if (newRoot == state.root)
            state.setMessage(s"${path}: no such file or directory!")
        else
            State(newRoot, newRoot.findDescendant(state.wd.path.substring(1)))
    }

}
