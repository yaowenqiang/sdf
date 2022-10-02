package com.jack.scala.oop.files

import com.jack.scala.oop.filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, val contents: String) extends DirEntry(parentPath, name) {
    override def getType: String = "File"
    def asFile: File = this
    override def asDirectory: Directory = throw new FileSystemException("file can not be converted to a directory")
    def isDirectory: Boolean = false
    def isFile: Boolean = true
    def setContents(newContents: String) : File =
        new File(parentPath, name, newContents)

    def appendContents(newContents: String) : File =
        setContents(contents + "\n" + newContents)

}
object File {
    def empty(parentPath: String, name: String): File =
        new File(parentPath ,name ,"")
}
