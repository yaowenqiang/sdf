package com.jack.scala.oop.commands
import com.jack.scala.oop.files.{DirEntry, Directory}
import com.jack.scala.oop.filesystem.State

import scala.annotation.tailrec

class Cd(dir: String) extends Command {
    override def apply(state: State): State = {
        val root = state.root
        val wd = state.wd
        val absPath = {
            if(dir.startsWith(Directory.SEPARATOR)) dir
            else if (wd.isRoot) wd.path + dir
            else wd.path + Directory.SEPARATOR + dir
        }

        val destinationDir = doFindEntry(root, absPath)

        if ((destinationDir == null) || (!destinationDir.isDirectory)) {
           state.setMessage(s"${dir}: no such directory")
        } else {
            State(root, destinationDir.asDirectory)
        }
    }

    def doFindEntry(root: Directory, path: String) : DirEntry = {
        val tokens :List[String] = path.substring(1).split(Directory.SEPARATOR).toList
        @tailrec
        def findEntryHelper(currentDirectory: Directory, path:List[String]) :DirEntry = {
            if(path.isEmpty || path.head.isEmpty) currentDirectory
            else if (path.tail.isEmpty) currentDirectory.findEntry(path.head)
            else {
                val nextDir = currentDirectory.findEntry(path.head)
                if (nextDir == null || !nextDir.isDirectory) null
                else findEntryHelper(nextDir.asDirectory, path.tail)
            }
        }

        findEntryHelper(root,tokens)
    }
}
