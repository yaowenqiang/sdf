package com.jack.scala.oop.commands
import com.jack.scala.oop.filesystem.State

import scala.annotation.tailrec

class Echo(args: Array[String]) extends Command {
    override def apply(state: State): State = {
        if (args.isEmpty) state
        else if (args.length == 1) state.setMessage(args(0))
        else {
            val operator = args(args.length - 2)
            var fileName = args(args.length -1)
            var contents = createContent(args, args.length -2)

            if (">>".equals(operator)) {
                doEcho(state, contents, fileName, append = true)
            } else if (">".equals(operator)) {
                doEcho(state, contents, fileName, append = false)
            } else {
                state.setMessage(createContent(args, args.length))
            }

        }
    }

    def doEcho(state: State, contents: String, fileName: String, append: Boolean = false) :State = ???

    // top index no-inclusive
    def createContent(args: Array[String], topIndex: Int) :String = {
        @tailrec
        def createContentHelper(currentIndex: Int, accumulator: String) :String = {
            if (currentIndex >= topIndex) accumulator
            else createContentHelper(currentIndex + 1, accumulator + " " + args(currentIndex))
        }
        createContentHelper(0, "")
    }


}
