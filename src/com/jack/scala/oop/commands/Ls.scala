package com.jack.scala.oop.commands
import com.jack.scala.oop.files.{DirEntry, Directory}
import com.jack.scala.oop.filesystem.State

class Ls extends Command {
    override def apply(state: State): State = {
        val contents = state.wd.contents
        val niceOutput :String = createNiceOutput(contents)
        state.setMessage(niceOutput)
    }
    def createNiceOutput(contents: List[DirEntry]) : String =
        if (contents.isEmpty) ""
        else {
            val entry = contents.head
            entry.name + "[" + entry.getType + "]" + "\n" + createNiceOutput(contents.tail)
        }

}
